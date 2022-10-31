package com.d34th.nullpointer.baseconvert.models

import androidx.annotation.StringRes
import androidx.lifecycle.SavedStateHandle
import com.d34th.nullpointer.baseconvert.core.delegates.SavableComposeState
import kotlinx.coroutines.Job

class WorkConvert(
    val base: Int,
    var jobConvert: Job? = null,
    savedStateHandle: SavedStateHandle
) {
    companion object {
        private const val RESOURCE_DEFAULT = -1
        private const val KEY_CURRENT_VALUE = "KEY_CURRENT_VALUE_"
        private const val KEY_ERROR_VALUE = "KEY_ERROR_VALUE_"
    }

    var currentValue by SavableComposeState(
        savedStateHandle = savedStateHandle,
        key = "${KEY_CURRENT_VALUE}_$base",
        defaultValue = ""
    )
        private set

    var errorValue by SavableComposeState(
        savedStateHandle = savedStateHandle,
        key = "${KEY_ERROR_VALUE}_$base",
        defaultValue = RESOURCE_DEFAULT
    )
        private set


    val hasError get() = errorValue != RESOURCE_DEFAULT


    fun changeValue(newValue: String) {
        currentValue = newValue
        errorValue = RESOURCE_DEFAULT
    }


    fun setError(@StringRes newError: Int) {
        errorValue = newError
    }
}