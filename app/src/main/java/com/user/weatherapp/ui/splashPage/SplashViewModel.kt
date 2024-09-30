package com.user.weatherapp.ui.splashPage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.user.weatherapp.data.local.AppOpenStatus
import com.user.weatherapp.data.local.UserPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val userPreferences: UserPreferences,
) : ViewModel() {

    val appOpenStatus = userPreferences.appOpenStatusFlow

    suspend fun updateAppOpenStatus(appOpenStatus: AppOpenStatus){
        userPreferences.setFirstTime(appOpenStatus)
    }

}