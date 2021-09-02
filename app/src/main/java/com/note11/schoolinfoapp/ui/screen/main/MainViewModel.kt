package com.note11.schoolinfoapp.ui.screen.main

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.note11.schoolinfoapp.data.LunchModel
import com.note11.schoolinfoapp.data.SubjectModel
import com.note11.schoolinfoapp.data.TimeModel
import java.util.*

class MainViewModel : ViewModel() {
    val progress = MutableLiveData(0f)
    val period = MutableLiveData("0교시")
    val className = MutableLiveData("수업 시작 전")
    val nextPeriod = MutableLiveData("")
    var timeInfo = MutableLiveData<TimeModel>()

    val subjectList = MutableLiveData<List<SubjectModel>>(listOf())
    val lunchList = MutableLiveData<List<LunchModel>>(listOf())
    val timeTableTime = MutableLiveData<List<String>>(listOf())

    fun checkNowPeriod() {
        val timeListTmp = timeTableTime.value!!
        val nowTime = SimpleDateFormat("HH:mm", Locale.KOREA).format(Calendar.getInstance().time)
        var nowPeriod = 0

        if (nowTime >= timeListTmp[0]) {
            for (i in timeListTmp.indices) if (nowTime < timeListTmp[i]) {
                nowPeriod = i
                break
            }

            if (nowPeriod == 0) nowPeriod = timeListTmp.size
        }

        setNowPeriod(nowPeriod)
    }

    private fun setNowPeriod(period: Int) {
        val endPeriod = subjectList.value!!.size
        progress.value = 100f * (period.toFloat() / endPeriod.toFloat())
        this.period.value = "${period}교시"
        if (period != 0) className.value = subjectList.value!![period - 1].subjectTitle
        if (period != endPeriod) nextPeriod.value =
            "${period + 1}교시 ${subjectList.value!![period].subjectTitle}"
    }

    fun setTime() {
        val size = subjectList.value!!.size
        val (classTime, restTime, firstTime, classBeforeLunch, lunchEndTime) = timeInfo.value!!
        val time = Calendar.getInstance()
        val timeTableStrList = mutableListOf<String>()
        val timeFormat = SimpleDateFormat("HH:mm", Locale.KOREA)

        time.set(0, 0, 0, firstTime[0], firstTime[1])

        for (i in 0 until classBeforeLunch.toInt()) {
            timeTableStrList.add(timeFormat.format(time.time))
            time.add(Calendar.MINUTE, classTime.toInt() + restTime.toInt())
        }

        time.set(0, 0, 0, lunchEndTime[0], lunchEndTime[1])

        for (i in classBeforeLunch.toInt() until size) {
            timeTableStrList.add(timeFormat.format(time.time))
            time.add(Calendar.MINUTE, classTime.toInt() + restTime.toInt())
        }

        timeTableTime.value = timeTableStrList
    }

    fun addTimeAtSubjectList() {
        val subjects = subjectList.value!!
        for (i in subjects.indices)
            subjectList.value!![i].time = "${timeTableTime.value!![i]}~"
    }

}