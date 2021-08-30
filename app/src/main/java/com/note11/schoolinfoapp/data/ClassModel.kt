package com.note11.schoolinfoapp.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ClassModel(
    @SerializedName("GRADE") val grade: String,
    @SerializedName("CLASS_NM") val classNum: String
) : Parcelable
