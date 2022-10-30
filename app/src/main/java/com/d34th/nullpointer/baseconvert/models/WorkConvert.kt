package com.d34th.nullpointer.baseconvert.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.d34th.nullpointer.baseconvert.R
import com.d34th.nullpointer.baseconvert.core.delegates.PropertyBase
import kotlinx.coroutines.Job

class WorkConvert(
    maxSize: Int,
    val base: Int,
) {
    val propertyBase: PropertyBase
    var jobConvert: Job? = null
    var hasOverflow by mutableStateOf(false)

    init {
        propertyBase = PropertyBase(
            base = base,
            hint = R.string.name_base,
            label = R.string.name_base,
            maxLength = maxSize,
            emptyError = R.string.empty_base,
            lengthError = R.string.length_base_error
        )
    }

}