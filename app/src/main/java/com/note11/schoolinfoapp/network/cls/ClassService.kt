package com.note11.schoolinfoapp.network.cls

import android.icu.util.Calendar
import com.note11.schoolinfoapp.data.ClassResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ClassService {
    @GET("/hub/classInfo?Type=json")
    suspend fun getClassInfo(
        @Query("KEY") apiKey: String,
        @Query("ATPT_OFCDC_SC_CODE") officeCode: String,
        @Query("SD_SCHUL_CODE") schoolCode: String,
        @Query("AY") year: String = Calendar.getInstance().get(Calendar.YEAR).toString()
    ) : Response<ClassResponse?>
}