package com.note11.schoolinfoapp.network

import com.note11.schoolinfoapp.network.cls.ClassService
import com.note11.schoolinfoapp.network.lunch.LunchService
import com.note11.schoolinfoapp.network.school.SchoolService
import com.note11.schoolinfoapp.network.time.TimeService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitUtil {
    private const val baseUrl = "https://open.neis.go.kr"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    val schoolService: SchoolService = retrofit.create(SchoolService::class.java)

    val classService: ClassService = retrofit.create(ClassService::class.java)

    val timeService: TimeService = retrofit.create(TimeService::class.java)

    val lunchService : LunchService = retrofit.create(LunchService::class.java)

}