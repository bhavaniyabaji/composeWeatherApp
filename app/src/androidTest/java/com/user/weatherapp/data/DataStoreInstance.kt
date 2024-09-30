package com.user.weatherapp.data

import android.content.Context
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.test.core.app.ApplicationProvider
import com.user.weatherapp.di.TEST_DATASTORE_NAME
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

object DataStoreInstance {

    val testScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    val prefDataStore = PreferenceDataStoreFactory.create(
        corruptionHandler = ReplaceFileCorruptionHandler(
            produceNewData = { emptyPreferences() }
        ),
        scope = testScope,
        produceFile = { ApplicationProvider.getApplicationContext<Context>()
            .preferencesDataStoreFile(TEST_DATASTORE_NAME) }
    )
}