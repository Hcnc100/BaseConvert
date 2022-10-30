package com.d34th.nullpointer.baseconvert.models

import com.d34th.nullpointer.baseconvert.R
import com.d34th.nullpointer.baseconvert.core.delegates.PropertyBase
import kotlinx.coroutines.Job

class WorkConvert(
    maxSize: Int,
    val base: Int,
) {
    val propertyBase: PropertyBase
    var jobConvert: Job? = null

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