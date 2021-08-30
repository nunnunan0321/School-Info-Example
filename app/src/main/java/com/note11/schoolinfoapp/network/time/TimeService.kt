package com.note11.schoolinfoapp.network.time

import com.note11.schoolinfoapp.data.ElsTimeResponse
import com.note11.schoolinfoapp.data.HisTimeResponse
import com.note11.schoolinfoapp.data.MisTimeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TimeService {
    @GET("/hub/hisTimetable?Type=json")
    suspend fun getHisTimeTable(
        @Query("KEY") apiKey: String,
        @Query("SD_SCHUL_CODE") schoolCode: String,
        @Query("ATPT_OFCDC_SC_CODE") officeCode: String,
        @Query("GRADE") grade: String,
        @Query("CLASS_NM") classNum: String,
        @Query("ALL_TI_YMD") date: String
    ) : Response<HisTimeResponse?>

    @GET("/hub/misTimetable?Type=json")
    suspend fun getMisTimeTable(
        @Query("KEY") apiKey: String,
        @Query("SD_SCHUL_CODE") schoolCode: String,
        @Query("ATPT_OFCDC_SC_CODE") officeCode: String,
        @Query("GRADE") grade: String,
        @Query("CLASS_NM") classNum: String,
        @Query("ALL_TI_YMD") date: String
    ) : Response<MisTimeResponse?>

    @GET("/hub/elsTimetable?Type=json")
    suspend fun getElsTimeTable(
        @Query("KEY") apiKey: String,
        @Query("SD_SCHUL_CODE") schoolCode: String,
        @Query("ATPT_OFCDC_SC_CODE") officeCode: String,
        @Query("GRADE") grade: String,
        @Query("CLASS_NM") classNum: String,
        @Query("ALL_TI_YMD") date: String
    ) : Response<ElsTimeResponse?>
}