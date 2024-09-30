package com.user.weatherapp.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.CoroutineScope


private const val TEST_DATASTORE_NAME: String = "test_datastore"

object MockDataStore{
    private val testContext: Context = ApplicationProvider.getApplicationContext()

    private var testDataStore :DataStore<Preferences>? = null

    fun getTestDataStore(scope : CoroutineScope) : DataStore<Preferences> {
        if(testDataStore == null){
            testDataStore = PreferenceDataStoreFactory.create(
                scope = scope,
                produceFile = { testContext.preferencesDataStoreFile(TEST_DATASTORE_NAME) }
            )
        }
        return testDataStore!!
    }

}
