package com.note11.schoolinfoapp.ui.screen.first.search

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.note11.schoolinfoapp.R
import com.note11.schoolinfoapp.data.SchoolModel
import com.note11.schoolinfoapp.databinding.ActivitySearchBinding
import com.note11.schoolinfoapp.ui.base.BaseActivity
import com.note11.schoolinfoapp.ui.screen.first.select.SelectActivity
import kotlinx.coroutines.flow.*


class SearchActivity : BaseActivity<ActivitySearchBinding>(R.layout.activity_search) {
    override val viewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        initRecyclerView()

        viewModel.searchQuery.observe(this, {
            if (it.length > 1) viewModel.search()
        })
    }

    private fun initRecyclerView() = with(binding) {
        val adapter = SearchAdapter { goNextStep(it) }
        rcvSearchList.layoutManager = LinearLayoutManager(this@SearchActivity)
        rcvSearchList.adapter = adapter
    }

    private fun goNextStep(info: SchoolModel) {
        val intent = Intent(this, SelectActivity::class.java)
        intent.putExtra("schoolInfo", info)
        startActivity(intent)
    }
}
