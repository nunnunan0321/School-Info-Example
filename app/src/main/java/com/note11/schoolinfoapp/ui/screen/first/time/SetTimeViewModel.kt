package com.note11.schoolinfoapp.ui.screen.first.time

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.note11.schoolinfoapp.data.TimeModel


class SetTimeViewModel : ViewModel() {
    val classTime = MutableLiveData("")
    val restTime = MutableLiveData("")
    val firstTimeHour = MutableLiveData("")
    val firstTimeMinute = MutableLiveData("")
    val classBeforeLunch = MutableLiveData("")
    val lunchEndTimeHour = MutableLiveData("")
    val lunchEndTimeMinute = MutableLiveData("")

    val lunchEndPeriod = MutableLiveData("점심시간은 언제 끝나나요?")

    fun getTimesByModel(): TimeModel? {
        return if (classTime.value!!.isEmpty() || restTime.value!!.isEmpty() || firstTimeHour.value!!.isEmpty() || firstTimeMinute.value!!.isEmpty() || classBeforeLunch.value!!.isEmpty() || lunchEndTimeHour.value!!.isEmpty() || lunchEndTimeMinute.value!!.isEmpty()) null
        else TimeModel(
            classTime = classTime.value!!,
            restTime = restTime.value!!,
            firstTime = listOf(firstTimeHour.value!!.toInt(), firstTimeMinute.value!!.toInt()),
            classBeforeLunch = classBeforeLunch.value!!,
            lunchEndTime = listOf(lunchEndTimeHour.value!!.toInt(), lunchEndTimeMinute.value!!.toInt())
        )
    }
}