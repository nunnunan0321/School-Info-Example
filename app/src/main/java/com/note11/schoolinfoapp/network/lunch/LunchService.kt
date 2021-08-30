package com.note11.schoolinfoapp.network.lunch

import com.note11.schoolinfoapp.data.HisTimeResponse
import com.note11.schoolinfoapp.data.LunchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LunchService {
    @GET("/hub/mealServiceDietInfo?Type=json")
    suspend fun getLunch(
        @Query("KEY") apiKey: String,
        @Query("SD_SCHUL_CODE") schoolCode: String,
        @Query("ATPT_OFCDC_SC_CODE") officeCode: String,
        @Query("MLSV_FROM_YMD") startDate: String,
        @Query("MLSV_TO_YMD") endDate: String
    ): Response<LunchResponse?>
}