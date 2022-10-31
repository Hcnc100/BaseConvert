package com.d34th.nullpointer.baseconvert.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d34th.nullpointer.baseconvert.domain.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    val listPrecision = (10..100 step 10).map { it.toString() }

    val currentPrecision = settingsRepository.currentPrecision.transform<Int, String> {
        emit(it.toString())
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        ""
    )

    fun changePrecision(precision: String) = viewModelScope.launch {
        settingsRepository.changePrecision(precision.toIntOrNull() ?: 10)
    }

}