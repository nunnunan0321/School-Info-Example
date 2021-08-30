package com.note11.schoolinfoapp.ui.screen.first.welcome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import com.note11.schoolinfoapp.R
import com.note11.schoolinfoapp.databinding.ActivityWelcomeBinding
import com.note11.schoolinfoapp.ui.base.BaseActivity
import com.note11.schoolinfoapp.ui.screen.first.search.SearchActivity

class WelcomeActivity : BaseActivity<ActivityWelcomeBinding>(R.layout.activity_welcome){
    override val viewModel: ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnWelcomeStart.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }
    }
}