package com.note11.schoolinfoapp.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SubjectModel(
    @SerializedName("ITRT_CNTNT") val subjectTitle: String,
    @SerializedName("PERIO") val period: String,
    @SerializedName("TIME") var time: String?
) : Parcelable