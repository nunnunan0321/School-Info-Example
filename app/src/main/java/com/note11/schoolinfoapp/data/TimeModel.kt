package com.note11.schoolinfoapp.data

data class TimeModel(
    val classTime: String, // 수업 시간 (분) ex. 40
    val restTime: String, // 쉬는 시간 (분) ex. 5
    val firstTime: List<Int>, // 첫 교시 시작 시간 ex. 8시 40분
    val classBeforeLunch: String // 점심 시간 이전 교시 ex. 4
)
