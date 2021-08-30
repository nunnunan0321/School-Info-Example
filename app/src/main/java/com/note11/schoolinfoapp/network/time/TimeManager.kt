package com.note11.schoolinfoapp.network.time

import com.note11.schoolinfoapp.data.SubjectModel
import com.note11.schoolinfoapp.data.UserModel
import com.note11.schoolinfoapp.network.RemoteDataSourceImpl

object TimeManager {
    suspend fun getTimeTable(userModel: UserModel): List<SubjectModel>? =
        RemoteDataSourceImpl.getTimeTable(userModel)

}