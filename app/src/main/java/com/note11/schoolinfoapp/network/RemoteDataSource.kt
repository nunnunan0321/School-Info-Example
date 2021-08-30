package com.note11.schoolinfoapp.network

import android.icu.util.Calendar
import com.note11.schoolinfoapp.data.*

interface RemoteDataSource {

    suspend fun getSearchList(searchString: String): List<SchoolModel>?

    suspend fun getClassInfo(schoolInfo: SchoolModel): List<ClassModel>?

    suspend fun getTimeTable(
        user: UserModel,
        date: Calendar = Calendar.getInstance()
    ): List<SubjectModel>?

    suspend fun getLunchList(
        schoolInfo: SchoolModel,
        startDate: String,
        endDate: String
    ): List<LunchModel>?
}