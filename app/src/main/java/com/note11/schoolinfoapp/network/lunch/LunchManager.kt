package com.note11.schoolinfoapp.network.lunch

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.util.Log
import com.note11.schoolinfoapp.data.LunchModel
import com.note11.schoolinfoapp.data.SchoolModel
import com.note11.schoolinfoapp.network.RemoteDataSourceImpl
import java.util.*

object LunchManager {
    suspend fun getLunch(schoolInfo: SchoolModel): List<LunchModel>? {
        val date = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("yyyyMMdd", Locale.KOREA)

        val today = dateFormat.format(date.time)
        date.add(Calendar.DAY_OF_MONTH, 7)
        val week = dateFormat.format(date.time)

        return RemoteDataSourceImpl.getLunchList(schoolInfo, today, week)
    }
}