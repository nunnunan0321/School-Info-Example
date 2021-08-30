package com.note11.schoolinfoapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    val schoolInfo: SchoolModel,
    val classInfo: ClassModel
) : Parcelable