package com.user.weatherapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.user.weatherapp.data.local.UserPreferences
import com.user.weatherapp.data.DataStoreInstance.prefDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

const val TEST_DATASTORE_NAME: String = "test_datastore"

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [UserPreferenceModule::class]
)
class UserPreferenceTestModule {

    @Singleton
    @Provides
    fun providePreferencesDataStore(@ApplicationContext appContext: Context): DataStore<Preferences> {
        return prefDataStore
    }

    @Singleton
    @Provides
    fun provideUserPreference(dataStore: DataStore<Preferences>): UserPreferences =
        UserPreferences(dataStore)
}
