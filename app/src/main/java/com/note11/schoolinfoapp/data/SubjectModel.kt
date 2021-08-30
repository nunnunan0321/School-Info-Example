package com.note11.schoolinfoapp.data

import com.google.gson.annotations.SerializedName

data class SubjectModel(
    @SerializedName("ITRT_CNTNT") val subjectTitle: String,
    @SerializedName("PERIO") val period: String,
    @SerializedName("TIME") var time: String = ""
)