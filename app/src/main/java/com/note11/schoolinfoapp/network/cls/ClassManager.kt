package com.note11.schoolinfoapp.network.cls

import com.note11.schoolinfoapp.data.ClassModel
import com.note11.schoolinfoapp.data.SchoolModel
import com.note11.schoolinfoapp.network.RemoteDataSourceImpl

object ClassManager {
    suspend fun getClassList(schoolInfo: SchoolModel) : List<ClassModel>? =
        RemoteDataSourceImpl.getClassInfo(schoolInfo)
}