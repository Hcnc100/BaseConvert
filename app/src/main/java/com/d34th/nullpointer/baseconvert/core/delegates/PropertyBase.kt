package com.d34th.nullpointer.baseconvert.core.delegates

import androidx.annotation.StringRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class PropertyBase(
    val base: Int,
) {
    companion object {
        private const val RESOURCE_DEFAULT = -1
    }


    var currentValue by mutableStateOf("")
        private set

    var errorValue by mutableStateOf(RESOURCE_DEFAULT)
        private set


    val hasError get() = errorValue != RESOURCE_DEFAULT

    val isEmpty get() = currentValue.isEmpty()


    fun changeValue(newValue: String) {
        currentValue = newValue
        errorValue = RESOURCE_DEFAULT
    }


    fun setAnotherError(@StringRes newError: Int) {
        errorValue = newError
    }


}