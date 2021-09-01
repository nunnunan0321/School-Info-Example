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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {
    var subjectList: List<SubjectModel>? = null
    var lunchList: List<LunchModel>? = null
    val load = MutableLiveData(0)

    fun getAllData(user: UserModel) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                subjectList = TimeManager.getTimeTable(user)
                load.postValue(load.value!!.plus(1))
            } catch (e: Exception) {
                Log.e("getAllData", e.toString())
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            try {
                lunchList = LunchManager.getLunch(user.schoolInfo)
                load.postValue(load.value!!.plus(1))
            } catch (e: Exception) {
                Log.e("getAllData", e.toString())
            }
        }
    }
}