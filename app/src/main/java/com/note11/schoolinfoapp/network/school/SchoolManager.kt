package com.note11.schoolinfoapp.network.school

import com.note11.schoolinfoapp.data.SchoolModel
import com.note11.schoolinfoapp.network.RemoteDataSourceImpl

object SchoolManager {
    suspend fun search(searchString: String): List<SchoolModel>? =
        RemoteDataSourceImpl.getSearchList(searchString)
}