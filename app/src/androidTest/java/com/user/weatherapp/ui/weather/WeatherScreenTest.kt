package com.user.weatherapp.ui.weather

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.compose.rememberNavController
import com.user.weatherapp.MainDispatcherRule
import com.user.weatherapp.data.repository.WeatherRepository
import com.user.weatherapp.ui.splashPage.SplashScreen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltAndroidTest
class WeatherScreenTest {


    @get:Rule(order = 1)
    var hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    val composeTestRule = createComposeRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Inject
    lateinit var repository: WeatherRepository

    lateinit var viewModelTest : WeatherViewModel

    @Before
    fun setup() {
        hiltTestRule.inject()
        viewModelTest = WeatherViewModel(repository)

    }

    @Test
    fun testWeatherScreenDisplayedWithBasicValues() = runTest{
        composeTestRule.setContent {
            WeatherScreen(viewModel = viewModelTest)
        }
        composeTestRule.awaitIdle()
        composeTestRule.onNodeWithTag("test_city").assertIsDisplayed()
        composeTestRule.onNodeWithTag("test_date").assertIsDisplayed()
        composeTestRule.onNodeWithTag("test_condition").assertIsDisplayed()
    }

    //TODO: should verify various whether scenarios by update test data(weather response)
    //TODO: should validate search bar
}