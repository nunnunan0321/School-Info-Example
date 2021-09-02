package com.note11.schoolinfoapp.ui.screen.splash

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.note11.schoolinfoapp.data.LunchModel
import com.note11.schoolinfoapp.data.SubjectModel
import com.note11.schoolinfoapp.data.UserModel
import com.note11.schoolinfoapp.network.lunch.LunchManager
import com.note11.schoolinfoapp.network.time.TimeManager
import com.note11.schoolinfoapp.util.DataUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

class SplashViewModel : ViewModel() {
    var subjectList: List<SubjectModel>? = null
    var lunchList: List<LunchModel>? = null
    val loaded = MutableLiveData(false)

    fun getAllData(user: UserModel) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val subjects = async { TimeManager.getTimeTable(user) }
            val lunches = async { LunchManager.getLunch(user.schoolInfo) }
            subjectList = subjects.await()
            lunchList = lunches.await()
            loaded.postValue(true)
        } catch (e: Exception) {
            Log.e("getAllData", e.toString())
        }
    }
}