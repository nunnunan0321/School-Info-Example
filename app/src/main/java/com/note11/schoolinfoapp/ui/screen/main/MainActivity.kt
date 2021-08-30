package com.note11.schoolinfoapp.ui.screen.main

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.note11.schoolinfoapp.R
import com.note11.schoolinfoapp.databinding.ActivityMainBinding
import com.note11.schoolinfoapp.ui.base.BaseActivity
import com.note11.schoolinfoapp.util.DataUtil
import kotlinx.coroutines.launch

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        viewModel.setNowPeriod(3, 7)

        initActivity()

        lifecycleScope.launch {
            val user = DataUtil(this@MainActivity).getUserInfoOnce()!!
            viewModel.getAllData(user)
        }
    }

    private fun initActivity() {
        val subjectAdapter = SubjectAdapter()
        val lunchAdapter = LunchAdapter()

        binding.recyclerMainTime.let {
            it.layoutManager = GridLayoutManager(this, 2)
            it.adapter = subjectAdapter
        }

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        binding.recyclerMainLunch.let {
            it.layoutManager = layoutManager
            it.adapter = lunchAdapter
        }
    }
}
