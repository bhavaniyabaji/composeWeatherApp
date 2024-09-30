package com.user.weatherapp.ui.weather

import junit.framework.TestCase.assertEquals
import com.user.weatherapp.MainDispatcherRule
import com.user.weatherapp.data.repository.WeatherRepository
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.MockKAnnotations
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltAndroidTest
class WeatherViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Inject
    lateinit var weatherRepository: WeatherRepository

    @BindValue
    var weatherViewModel = mockk<WeatherViewModel>(relaxed = true)

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
        MockKAnnotations.init(this, true)
    }

    @Test
    fun fetchWeatherData_returns_correct_data() = runTest {
//        // Given
//        val mockWeatherData = WeatherData(temperature = 25.0)
//        Mockito.`when`(weatherRepository.getWeather()).thenReturn(mockWeatherData)

        // When
        weatherViewModel.getWeather()

        // Then
        assertEquals(20, weatherViewModel.uiState.value.weather?.temperature)
    }

//    @Test
//    fun `when getWeather completes, it should emit success state`() = runTest {
//        viewModel.uiState.test {
//
//            assertEquals(WeatherUiState(weather = fakeWeather), awaitItem())
//        }
//    }
//
//    @Test
//    fun `when getWeather completes, it should emit success state with humidity of 60`() = runTest {
//        viewModel.uiState.test {
//
//            assertEquals(WeatherUiState(weather = fakeWeather), awaitItem())
//            assertEquals(WeatherUiState(weather = fakeWeather).weather?.humidity, 60)
//        }
//    }
}
