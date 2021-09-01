package com.note11.schoolinfoapp.ui.screen.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.note11.schoolinfoapp.data.LunchModel
import com.note11.schoolinfoapp.data.SubjectModel

class MainViewModel : ViewModel() {
    val progress = MutableLiveData(0f)
    val period = MutableLiveData("0교시")
    val className = MutableLiveData("과목명")
    val nextPeriod = MutableLiveData("")

    val subjectList = MutableLiveData<List<SubjectModel>>(listOf())
    val lunchList = MutableLiveData<List<LunchModel>>(listOf())

    fun setNowPeriod(period: Int, maxPeriod: Int) {
        progress.value = 100f * (period.toFloat() / maxPeriod.toFloat())
        this.period.value = "${period}교시"
        className.value = "과목"
        if (period != maxPeriod) nextPeriod.value = "${period + 1}교시 과목명"
    }
}