package com.user.weatherapp.ui.weather

import com.user.weatherapp.model.Weather

data class WeatherUiState(
    val weather: Weather? = null,
    val isLoading: Boolean = false,
    val errorMessage: String = "",
)
