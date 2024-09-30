package com.user.weatherapp.ui.splash

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.user.weatherapp.MainDispatcherRule
import com.user.weatherapp.data.local.AppOpenStatus
import com.user.weatherapp.data.local.UserPreferences
import com.user.weatherapp.di.TEST_DATASTORE_NAME
import com.user.weatherapp.ui.splashPage.SplashViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.File
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltAndroidTest
class SplashViewModelTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Inject
    lateinit var userPreferences: UserPreferences

    @BindValue
    var viewModelTest = mockk<SplashViewModel>(relaxed = true)

    @Before
    fun before(){
        hiltRule.inject()
        viewModelTest = SplashViewModel(userPreferences)
    }

    @Test
    fun `verifyAppOpenStatusForNonFirstTimer`() = runTest{
        viewModelTest.updateAppOpenStatus(AppOpenStatus.NON_FIRST_TIMER)
        advanceUntilIdle()
        val result = viewModelTest.appOpenStatus.firstOrNull()

        assertEquals(AppOpenStatus.NON_FIRST_TIMER, result)
    }
}