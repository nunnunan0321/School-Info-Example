package com.note11.schoolinfoapp.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.note11.schoolinfoapp.R

abstract class BaseActivity<B : ViewDataBinding>(
    @LayoutRes private val layoutResID: Int
) : AppCompatActivity() {
    lateinit var binding: B
    abstract val viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.slide_left, R.anim.slide_left_before)
        binding = DataBindingUtil.setContentView(this, layoutResID)
        binding.lifecycleOwner = this
    }
}