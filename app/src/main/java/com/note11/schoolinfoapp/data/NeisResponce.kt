package com.note11.schoolinfoapp.data

import com.google.gson.annotations.SerializedName

data class InfoResponse(
    @SerializedName("schoolInfo") val schoolInfo : List<Map<String, List<SchoolModel>>>
)

data class ClassResponse(
    @SerializedName("classInfo") val classInfo : List<Map<String, List<ClassModel>>>
)

data class HisTimeResponse(
    @SerializedName("hisTimetable") val timeTable : List<Map<String, List<SubjectModel>>>
)

data class MisTimeResponse(
    @SerializedName("misTimetable") val timeTable : List<Map<String, List<SubjectModel>>>
)

data class ElsTimeResponse(
    @SerializedName("elsTimetable") val timeTable : List<Map<String, List<SubjectModel>>>
)

data class LunchResponse(
    @SerializedName("mealServiceDietInfo") val lunch :  List<Map<String, List<LunchModel>>>
)