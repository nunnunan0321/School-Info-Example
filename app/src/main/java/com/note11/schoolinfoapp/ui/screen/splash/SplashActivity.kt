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

class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    override val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadData()

        viewModel.load.observe(this, {
            if (it == 2) goToMain(viewModel.subjectList, viewModel.lunchList)
        })
    }

    private fun loadData() = lifecycleScope.launch {
        val user = DataUtil(this@SplashActivity).getUserInfoOnce() //유저 정보를 가져온다.

        if (user != null) { //있다면 -> 이전에 데이터를 저장 했다면 메인 액티비티로
            viewModel.getAllData(user)
        } else { // 없다면 -> 처음 접속한다면 웰컴으로
            startActivity(Intent(this@SplashActivity, WelcomeActivity::class.java))
            this@SplashActivity.finish()
        }
    }

    private fun goToMain(subjectList: List<SubjectModel>?, lunchList: List<LunchModel>?) {
        val lunchArrayList = arrayListOf<LunchModel>()
        val subjectArrayList = arrayListOf<SubjectModel>()
        lunchList?.let { lunchArrayList.addAll(it) }
        subjectList?.let { subjectArrayList.addAll(it) }

        Intent(this, MainActivity::class.java).also {
            it.putParcelableArrayListExtra("lunchList", lunchArrayList)
            it.putParcelableArrayListExtra("subjectList", subjectArrayList)

            lifecycleScope.launch {
                // TODO: 2021-09-01 800ms 딜레이를 주고 MainActivity로 이동해주기
                delay(800)
                startActivity(it)
                finish()
            }
        }
    }

}