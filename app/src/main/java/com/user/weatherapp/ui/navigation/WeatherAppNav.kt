package com.user.weatherapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.user.weatherapp.data.local.UserPreferences
import com.user.weatherapp.ui.splashPage.SplashScreen
import com.user.weatherapp.ui.weather.WeatherScreen

@Composable
fun WeatherAppNav() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = PageRoutes.WELCOME.name
    ) {
        composable(PageRoutes.WELCOME.name) {
            SplashScreen(navController = navController)
        }
        composable(PageRoutes.HOME.name) {
            WeatherScreen()
        }
    }
}