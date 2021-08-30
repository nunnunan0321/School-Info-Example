package com.note11.schoolinfoapp.network.school

import com.note11.schoolinfoapp.data.InfoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SchoolService {
    @GET("/hub/schoolInfo?Type=json")
    suspend fun getSchoolInfo(
        @Query("KEY") apiKey: String,
        @Query("SCHUL_NM") schoolName: String
    ) : Response<InfoResponse?>
}