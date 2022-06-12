package com.rechit.share.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.rechit.share.ui.UserPreference
import kotlinx.coroutines.launch

class MainViewModel(private val pref: UserPreference) : ViewModel() {
    fun isFirstTimeLaunch(): LiveData<Boolean> {
        return pref.isFirstTimeLaunch().asLiveData()
    }

    fun setFirstTimeLaunch(isDarkModeActive: Boolean) {
        viewModelScope.launch {
            pref.setFirstTimeLaunch(isDarkModeActive)
        }
    }
}