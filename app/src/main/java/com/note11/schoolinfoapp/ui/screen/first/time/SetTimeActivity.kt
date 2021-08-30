package com.note11.schoolinfoapp.ui.screen.first.time

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import com.note11.schoolinfoapp.R
import com.note11.schoolinfoapp.data.UserModel
import com.note11.schoolinfoapp.databinding.ActivitySetTimeBinding
import com.note11.schoolinfoapp.ui.base.BaseActivity
import com.note11.schoolinfoapp.ui.screen.main.MainActivity
import com.note11.schoolinfoapp.util.DataUtil
import kotlinx.coroutines.launch

class SetTimeActivity : BaseActivity<ActivitySetTimeBinding>(R.layout.activity_set_time) {
    override val viewModel: SetTimeViewModel by viewModels()
    private lateinit var receivedInfo: UserModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivity()
    }

    private fun initActivity() {
        receivedInfo = intent.getParcelableExtra("userInfo")!!
        binding.vm = viewModel

        binding.btnTimeNext.setOnClickListener { endToSetUp() }
    }

    private fun endToSetUp() = let { act ->
        val time = viewModel.getTimesByModel()

        if (time == null) Toast.makeText(act, "입력하지 않은 값이 존재해요", Toast.LENGTH_SHORT).show()
        else lifecycleScope.launch {
            DataUtil(act).run {
                setUserInfo(receivedInfo)
                setTimeInfo(time)
            }
            startActivity(Intent(act, MainActivity::class.java))
            ActivityCompat.finishAffinity(act)
        }
    }
}