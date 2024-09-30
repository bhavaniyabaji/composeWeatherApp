package com.user.weatherapp.data.repository

import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import com.user.weatherapp.data.model.toWeather
import com.user.weatherapp.data.network.WeatherApi
import com.user.weatherapp.model.Weather
import com.user.weatherapp.utils.Result
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class WeatherRepositoryTest {
    private lateinit var repository: DefaultWeatherRepository
    private val weatherApi = mockk<WeatherApi>()

    @Before
    fun setup() {
        repository = DefaultWeatherRepository(weatherApi)
    }

    @Test
    fun `when getWeatherForecast is called, it should emit loading state and then success state`() =
        runTest {
            coEvery {
                weatherApi.getWeatherForecast(
                    any(),
                    any(),
                    any()
                )
            } returns sampleForecastResponse

            val results = mutableListOf<Result<Weather>>()
            repository.getWeatherForecast("Munich").collect { result ->
                results.add(result)
            }
            assertEquals(Result.Loading, results[0])

            assertEquals(Result.Success(sampleForecastResponse.toWeather()), results[1])
        }
}