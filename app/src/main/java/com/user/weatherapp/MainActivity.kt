package com.user.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.user.weatherapp.data.local.UserPreferences
import com.user.weatherapp.ui.navigation.WeatherAppNav
import dagger.hilt.android.AndroidEntryPoint
import com.user.weatherapp.ui.theme.WeatherTheme
import com.user.weatherapp.ui.weather.WeatherScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WeatherTheme {
                WeatherAppNav()
            }
        }
    }
}
