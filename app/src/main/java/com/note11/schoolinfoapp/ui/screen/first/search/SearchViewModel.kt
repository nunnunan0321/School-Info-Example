package com.note11.schoolinfoapp.ui.screen.first.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.note11.schoolinfoapp.data.SchoolModel
import com.note11.schoolinfoapp.network.school.SchoolManager
import kotlinx.coroutines.*

class SearchViewModel : ViewModel() {
    var searchList = MutableLiveData<List<SchoolModel>>(listOf())
    val searchQuery = MutableLiveData("")

    fun search() = viewModelScope.launch(Dispatchers.IO) {
        try {
            val searchList = SchoolManager.search(searchQuery.value!!)
            searchList?.let { this@SearchViewModel.searchList.postValue(it) }
        } catch (e: Exception) {
            Log.e("SearchViewModel", e.toString())
        }
    }

}