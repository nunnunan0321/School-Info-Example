package com.note11.schoolinfoapp.ui.screen.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.note11.schoolinfoapp.R
import com.note11.schoolinfoapp.databinding.ActivitySplashBinding
import com.note11.schoolinfoapp.ui.screen.first.welcome.WelcomeActivity
import com.note11.schoolinfoapp.ui.screen.main.MainActivity
import com.note11.schoolinfoapp.util.DataUtil
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        binding.lifecycleOwner = this

        loadData()
    }

    private fun loadData() = lifecycleScope.launch {
        val user = DataUtil(this@SplashActivity).getUserInfoOnce()

        if (user != null) {
            delay(1500)
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        } else {
            startActivity(Intent(this@SplashActivity, WelcomeActivity::class.java))
        }

        this@SplashActivity.finish()
    }

}