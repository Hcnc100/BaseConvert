package com.d34th.nullpointer.baseconvert.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingsDataSourceImpl(
    private val context: Context
) : SettingsDataSource {

    companion object {
        private const val KEY_PRECISION = "KEY_PRECISION"
        private const val NAME_PREF_USER = "NAME_PREF_USER"
    }

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = NAME_PREF_USER)
    private val keyPrecision = intPreferencesKey(KEY_PRECISION)

    override val currentPrecision: Flow<Int> = context.dataStore.data.map { pref ->
        pref[keyPrecision] ?: 10
    }

    override suspend fun changePrecision(precision: Int) {
        context.dataStore.edit { pref ->
            pref[keyPrecision] = precision
        }
    }
}