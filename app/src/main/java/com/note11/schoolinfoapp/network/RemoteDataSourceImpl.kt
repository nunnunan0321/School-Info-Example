package com.note11.schoolinfoapp.network

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.util.Log
import com.note11.schoolinfoapp.data.*
import retrofit2.Response
import java.util.*

object RemoteDataSourceImpl : RemoteDataSource {
    override suspend fun getSearchList(searchString: String): List<SchoolModel>? {
        val res = RetrofitUtil.schoolService.getSchoolInfo(
            apiKey = API_KEY,
            schoolName = searchString
        )

        if (res.isSuccessful.not()) {
            Log.e("getClassInfoRetrofit", res.toString())
            return null
        }

        return res.body()?.schoolInfo?.get(1)?.get("row")
    }

    override suspend fun getClassInfo(schoolInfo: SchoolModel): List<ClassModel>? {
        val res = RetrofitUtil.classService.getClassInfo(
            apiKey = API_KEY,
            officeCode = schoolInfo.eduOfficeCode,
            schoolCode = schoolInfo.schoolCode
        )

        if (res.isSuccessful.not()) {
            Log.e("getClassInfoRetrofit", res.toString())
            return null
        }

        return res.body()?.classInfo?.get(1)?.get("row")
    }

    override suspend fun getTimeTable(user: UserModel, date: Calendar): List<SubjectModel>? {
        val service = RetrofitUtil.timeService
        val today = SimpleDateFormat("yyyyMMdd", Locale.KOREA).format(date.time)
        val (_, schoolCode, schoolKind, _, eduOfficeCode) = user.schoolInfo
        val (grade, classNum) = user.classInfo

        val res = when (schoolKind) {
            "고등학교" -> service.getHisTimeTable(
                API_KEY,
                schoolCode,
                eduOfficeCode,
                grade,
                classNum,
                today
            )
            "중학교" -> service.getMisTimeTable(
                API_KEY,
                schoolCode,
                eduOfficeCode,
                grade,
                classNum,
                today
            )
            "초등학교" -> service.getElsTimeTable(
                API_KEY,
                schoolCode,
                eduOfficeCode,
                grade,
                classNum,
                today
            )

            else -> return null
        }

        if (res.isSuccessful.not()) {
            Log.e("getTimeTableRetrofit", res.toString())
            return null
        }

        return when (schoolKind) {
            "고등학교" -> (res as Response<HisTimeResponse>).body()?.timeTable?.get(1)?.get("row")
            "중학교" -> (res as Response<MisTimeResponse>).body()?.timeTable?.get(1)?.get("row")
            "초등학교" -> (res as Response<ElsTimeResponse>).body()?.timeTable?.get(1)?.get("row")
            else -> null
        }
    }

    override suspend fun getLunchList(
        schoolInfo: SchoolModel,
        startDate: String,
        endDate: String
    ): List<LunchModel>? {

        val res = RetrofitUtil.lunchService.getLunch(
            API_KEY,
            schoolInfo.schoolCode,
            schoolInfo.eduOfficeCode,
            startDate,
            endDate
        )

        if (res.isSuccessful.not()) {
            Log.e("getClassInfoRetrofit", res.toString())
            return null
        }

        return res.body()?.lunch?.get(1)?.get("row")
    }

    private const val API_KEY = "f5f2f940e4d64f99bf12b9477e02d01a"
}