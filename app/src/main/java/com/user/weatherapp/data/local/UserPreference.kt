package com.user.weatherapp.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserPreferences @Inject constructor(private val dataStore: DataStore<Preferences>) {

    private val FIRST_TIME_KEY = intPreferencesKey("first_time")

    // Get the first-time flag from DataStore
    val appOpenStatusFlow: Flow<AppOpenStatus> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                // Handle error and return default value in case of failure
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[FIRST_TIME_KEY]?.toAppOpenStatus() ?: AppOpenStatus.FIRST_TIMER
        }

    // Update the first-time flag in DataStore
    suspend fun setFirstTime(appOpenStatus: AppOpenStatus) {
        dataStore.edit { preferences ->
            preferences[FIRST_TIME_KEY] = appOpenStatus.ordinal
        }
    }
}

fun Int.toAppOpenStatus(): AppOpenStatus?{
    return AppOpenStatus.values().firstOrNull { it.ordinal == this }
}

enum class AppOpenStatus{
    FIRST_TIMER,
    NON_FIRST_TIMER,
    UNKNOWN
}