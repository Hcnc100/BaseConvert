package com.d34th.nullpointer.baseconvert.domain

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    val currentPrecision: Flow<Int>
    suspend fun changePrecision(precision: Int)
}