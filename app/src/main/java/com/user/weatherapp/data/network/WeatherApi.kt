package com.user.weatherapp.data.network

import com.user.weatherapp.BuildConfig
import com.user.weatherapp.data.model.ForecastResponse
import com.user.weatherapp.utils.DEFAULT_WEATHER_DESTINATION
import com.user.weatherapp.utils.NUMBER_OF_DAYS
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("forecast.json")
    suspend fun getWeatherForecast(
        @Query("key") key: String = BuildConfig.API_KEY,
        @Query("q") city: String = DEFAULT_WEATHER_DESTINATION,
        @Query("days") days: Int = NUMBER_OF_DAYS,
    ): ForecastResponse
}