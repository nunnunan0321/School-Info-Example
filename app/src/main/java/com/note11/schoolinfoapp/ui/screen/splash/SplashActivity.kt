package com.note11.schoolinfoapp.ui.screen.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.note11.schoolinfoapp.R
import com.note11.schoolinfoapp.data.LunchModel
import com.note11.schoolinfoapp.data.SubjectModel
import com.note11.schoolinfoapp.databinding.ActivitySplashBinding
import com.note11.schoolinfoapp.ui.base.BaseActivity
import com.note11.schoolinfoapp.ui.screen.first.welcome.WelcomeActivity
import com.note11.schoolinfoapp.ui.screen.main.MainActivity
import com.note11.schoolinfoapp.util.DataUtil
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    override val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.loaded.observe(this, {
            if (it) goToMain(viewModel.subjectList, viewModel.lunchList)
        })

        loadData()
    }

    private fun loadData() = lifecycleScope.launch {
        val user = DataUtil(this@SplashActivity).getUserInfoOnce()

        if (user != null) {
            viewModel.getAllData(user)
        } else {
            startActivity(Intent(this@SplashActivity, WelcomeActivity::class.java))
            this@SplashActivity.finish()
        }
    }

    private fun goToMain(subjectList: List<SubjectModel>?, lunchList: List<LunchModel>?) {
        val lunchArrayList = lunchListProcessing(lunchList)
        val subjectArrayList = arrayListOf<SubjectModel>()
        subjectList?.let { subjectArrayList.addAll(it) }

        Intent(this, MainActivity::class.java).also {
            it.putParcelableArrayListExtra("lunchList", lunchArrayList)
            it.putParcelableArrayListExtra("subjectList", subjectArrayList)

            lifecycleScope.launch {
                it.putExtra("storedTimeInfo", DataUtil(applicationContext).getTimeInfoOnce())
                delay(800)
                startActivity(it)
                finish()
            }
        }
    }

    private fun lunchListProcessing(list: List<LunchModel>?): ArrayList<LunchModel> {
        val newList = arrayListOf<LunchModel>()

        if (list == null) return newList

        for (i in list.indices) if (list[i].mealCode == "2") {
            val item = list[i]
            val regex1 = Regex("""[\d.]""")
            val regex2 = Regex("""<br/>""")
            var newMenuText = item.menu

            newMenuText = regex1.replace(newMenuText, "")
            newMenuText = regex2.replace(newMenuText, "\n")

            item.menu = newMenuText
            newList.add(item)
        }

        return newList
    }

}