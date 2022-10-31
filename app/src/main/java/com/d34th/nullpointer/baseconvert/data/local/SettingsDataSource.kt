package com.d34th.nullpointer.baseconvert.data.local

import kotlinx.coroutines.flow.Flow

interface SettingsDataSource {
    val currentPrecision: Flow<Int>
    suspend fun changePrecision(precision: Int)
}