package com.d34th.nullpointer.baseconvert.core.delegates

import androidx.annotation.StringRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class PropertyBase(
    val base: Int,
    @StringRes val hint: Int,
    @StringRes val label: Int,
    private val maxLength: Int,
    private val valueDefault: String = "",
    @StringRes private val emptyError: Int = RESOURCE_DEFAULT,
    @StringRes private val lengthError: Int = RESOURCE_DEFAULT,
) {
    companion object {
        private const val RESOURCE_DEFAULT = -1
    }


    var currentValue by mutableStateOf("")
        private set

    var errorValue by mutableStateOf(RESOURCE_DEFAULT)
        private set

    val hasChanged: Boolean get() = this.currentValue != valueDefault

    val countLength get() = "${currentValue.length}/${maxLength}"

    val hasError get() = errorValue != RESOURCE_DEFAULT

    val isEmpty get() = currentValue.isEmpty()


    fun changeValue(newValue: String, isInit: Boolean = false) {
        currentValue = newValue
        if (!isInit) {
            errorValue = when {
                newValue.isEmpty() && emptyError != RESOURCE_DEFAULT -> emptyError
                newValue.length > maxLength && lengthError != RESOURCE_DEFAULT -> lengthError
                else -> RESOURCE_DEFAULT
            }
        }
    }


    fun setAnotherError(@StringRes newError: Int) {
        errorValue = newError
    }

    fun clearValue() {
        currentValue = ""
        errorValue = RESOURCE_DEFAULT
    }

    fun reValueField() = changeValue(currentValue)

}