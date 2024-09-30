package com.user.weatherapp.ui.splash

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.compose.rememberNavController
import com.user.weatherapp.MainDispatcherRule
import com.user.weatherapp.data.local.UserPreferences
import com.user.weatherapp.ui.splashPage.SplashScreen
import com.user.weatherapp.ui.splashPage.SplashViewModel
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
class SplashScreenTest {


    @get:Rule(order = 1)
    var hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    val composeTestRule = createComposeRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Inject
    lateinit var userPreferences: UserPreferences

    lateinit var viewModelTest : SplashViewModel

    @Before
    fun setup() {
        hiltTestRule.inject()
        viewModelTest = SplashViewModel(userPreferences)

    }

    @Test
    fun verify_WelcomeText_ForFirstTimer() = runTest{
        composeTestRule.setContent {
            val navController = rememberNavController()
            SplashScreen(navController, viewModelTest)
        }
        composeTestRule.awaitIdle()
        composeTestRule.onNodeWithTag("Welcome message").assertIsDisplayed()

    }
}