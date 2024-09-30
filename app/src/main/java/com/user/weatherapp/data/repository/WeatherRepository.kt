package com.user.weatherapp.data.repository

import com.user.weatherapp.model.Weather
import com.user.weatherapp.utils.Result
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getWeatherForecast(city: String): Flow<Result<Weather>>
}