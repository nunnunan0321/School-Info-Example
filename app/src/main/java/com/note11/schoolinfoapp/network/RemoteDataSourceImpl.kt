package com.note11.schoolinfoapp.network

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.util.Log
import com.note11.schoolinfoapp.data.*
import retrofit2.Response
import java.util.*

object RemoteDataSourceImpl : RemoteDataSource {
    override suspend fun getSearchList(searchString: String): List<SchoolModel>? {
        RetrofitUtil.schoolService.getSchoolInfo(
            apiKey = API_KEY,
            schoolName = searchString
        ).run {
            if (isSuccessful.not()) {
                Log.e("getSearchInfoRetrofit", toString())
                return null
            }

            return body()?.schoolInfo?.get(1)?.get("row")
        }
    }

    override suspend fun getClassInfo(schoolInfo: SchoolModel): List<ClassModel>? {
        RetrofitUtil.classService.getClassInfo(
            apiKey = API_KEY,
            officeCode = schoolInfo.eduOfficeCode,
            schoolCode = schoolInfo.schoolCode
        ).run {
            if (isSuccessful.not()) {
                Log.e("getClassInfoRetrofit", toString())
                return null
            }

            return body()?.classInfo?.get(1)?.get("row")
        }
    }

    override suspend fun getTimeTable(user: UserModel, date: Calendar): List<SubjectModel>? {
        val service = RetrofitUtil.timeService
        val today = SimpleDateFormat("yyyyMMdd", Locale.KOREA).format(date.time)
        val (_, schoolCode, schoolKind, _, eduOfficeCode) = user.schoolInfo
        val (grade, classNum) = user.classInfo

        when (schoolKind) {
            "고등학교" -> {
                service.getHisTimeTable(
                    API_KEY,
                    schoolCode,
                    eduOfficeCode,
                    grade,
                    classNum,
                    today
                ).run {
                    return if (isSuccessful.not()) {
                        Log.e("getTimeTableRetrofit", toString())
                        null
                    } else body()?.timeTable?.get(1)?.get("row")
                }
            }
            "중학교" -> {
                service.getMisTimeTable(
                    API_KEY,
                    schoolCode,
                    eduOfficeCode,
                    grade,
                    classNum,
                    today
                ).run {
                    return if (isSuccessful.not()) {
                        Log.e("getTimeTableRetrofit", toString())
                        null
                    } else body()?.timeTable?.get(1)?.get("row")
                }
            }
            "초등학교" -> {
                service.getElsTimeTable(
                    API_KEY,
                    schoolCode,
                    eduOfficeCode,
                    grade,
                    classNum,
                    today
                ).run {
                    return if (isSuccessful.not()) {
                        Log.e("getTimeTableRetrofit", toString())
                        null
                    } else body()?.timeTable?.get(1)?.get("row")
                }
            }
            else -> return null
        }
    }

    override suspend fun getLunchList(
        schoolInfo: SchoolModel,
        startDate: String,
        endDate: String
    ): List<LunchModel>? {

        RetrofitUtil.lunchService.getLunch(
            API_KEY,
            schoolInfo.schoolCode,
            schoolInfo.eduOfficeCode,
            startDate,
            endDate
        ).run {
            if (isSuccessful.not()) {
                Log.e("getClassInfoRetrofit", toString())
                return null
            }

            return body()?.lunch?.get(1)?.get("row")
        }
    }

    private const val API_KEY = "f5f2f940e4d64f99bf12b9477e02d01a"
}