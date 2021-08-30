package com.note11.schoolinfoapp.ui.screen.first.select

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.note11.schoolinfoapp.data.SchoolModel
import com.note11.schoolinfoapp.network.cls.ClassManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SelectViewModel : ViewModel() {
    val gradeList: MutableLiveData<List<String>> by lazy { MutableLiveData<List<String>>(listOf()) }
    val classList: MutableLiveData<List<String>> by lazy { MutableLiveData<List<String>>(listOf()) }

    fun getClassList(schoolInfo: SchoolModel) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val result = ClassManager.getClassList(schoolInfo)

            val gradeListTMP = mutableListOf<String>()
            val classListTMP = mutableListOf<Int>()
            val classListForString = mutableListOf<String>()

            result?.map {
                gradeListTMP.add("${it.grade}학년")
                try {
                    classListTMP.add(it.classNum.toInt())
                } catch (e: Exception) {
                    classListForString.add("${it.classNum}반")
                }
            }

            gradeList.postValue(gradeListTMP.distinct().sorted())
            if (classListTMP.size > 0 && classListForString.size == 0) {
                classListTMP.distinct().sorted().map { classListForString.add("${it}반") }
                classList.postValue(classListForString)
            } else {
                classList.postValue(classListForString.distinct().sorted())
            }
        } catch (e: Exception) {
            Log.e("SelectViewModel", e.toString())
        }
    }
}