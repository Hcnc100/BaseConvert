package com.d34th.nullpointer.baseconvert.domain

import com.d34th.nullpointer.baseconvert.data.local.SettingsDataSource
import kotlinx.coroutines.flow.Flow

class SettingsRepoImpl(
    private val settingsDataSource: SettingsDataSource
) : SettingsRepository {
    override val currentPrecision: Flow<Int> = settingsDataSource.currentPrecision

    override suspend fun changePrecision(precision: Int) =
        settingsDataSource.changePrecision(precision)
}