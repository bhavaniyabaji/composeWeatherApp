package com.user.weatherapp.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mockk.MockKAnnotations
import io.mockk.unmockkAll
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class UserPreferencesTest {
    private val testCoroutineDispatcher = UnconfinedTestDispatcher()
    private val testCoroutineScope = TestScope(testCoroutineDispatcher + Job())

    private var testDataStore: DataStore<Preferences> = MockDataStore.getTestDataStore(testCoroutineScope)

    private var repository: UserPreferences =
         UserPreferences(testDataStore)

    @Before
    fun before(){
        MockKAnnotations.init(this, true)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun verify_initialUserPreferenceValue() = runTest{
        val result = repository.appOpenStatusFlow.first()
        assertEquals(AppOpenStatus.FIRST_TIMER, result)
    }

    @Test
    fun verify_userPreference_updatesNewValue() = runTest{
        //update appOpenStatus status
        repository.setFirstTime(AppOpenStatus.NON_FIRST_TIMER)
        advanceUntilIdle()
        //verify updated value
        val result = repository.appOpenStatusFlow.first()
        assertEquals(AppOpenStatus.NON_FIRST_TIMER, result)
    }
}