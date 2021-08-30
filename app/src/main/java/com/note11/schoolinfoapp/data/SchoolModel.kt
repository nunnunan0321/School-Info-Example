package com.note11.schoolinfoapp.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SchoolModel(
    @SerializedName("SCHUL_NM") val schoolName: String, //학교 이름 (ex. 선린인터넷고등학교)
    @SerializedName("SD_SCHUL_CODE") val schoolCode: String, //학교 코드 (ex. 7010536)
    @SerializedName("SCHUL_KND_SC_NM") val schoolKind: String, //학교 구분 (ex. 고등학교)
    @SerializedName("ATPT_OFCDC_SC_NM") val eduOfficeName: String, //교육청 이름 (ex. 서울특별시교육청)
    @SerializedName("ATPT_OFCDC_SC_CODE") val eduOfficeCode: String, //교육청 코드 (ex. B10)
) : Parcelable