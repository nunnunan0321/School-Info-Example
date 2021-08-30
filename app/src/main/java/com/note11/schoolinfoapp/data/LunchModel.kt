package com.note11.schoolinfoapp.data

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import com.google.gson.annotations.SerializedName
import java.util.*

data class LunchModel(
    @SerializedName("MMEAL_SC_CODE") val mealCode: String, // 1: 조식, 2: 중식, 3: 석식
    @SerializedName("MLSV_YMD") val mealDate: String,
    @SerializedName("DDISH_NM") val menu: String,
    @SerializedName("MEAL_DATE") var mealDateForLocale: String = ""
)
