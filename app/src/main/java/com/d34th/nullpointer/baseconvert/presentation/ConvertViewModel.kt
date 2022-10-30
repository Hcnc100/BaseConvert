package com.d34th.nullpointer.baseconvert.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.d34th.nullpointer.baseconvert.R
import com.d34th.nullpointer.baseconvert.core.delegates.PropertySavableBase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ConvertViewModel @Inject constructor(
    savedState: SavedStateHandle,
) : ViewModel() {

    companion object {
        private const val KEY_BASE = "KEY_BASE_CONVERT"
        private const val MAX_LENGTH_INPUT = 40
    }

    val listPropertyConvert = (2..16).map {
        mapOf(
            it to PropertySavableBase(
                tagSavable = "${KEY_BASE}_$it",
                hint = R.string.name_base,
                label = R.string.label_base,
                maxLength = MAX_LENGTH_INPUT,
                savedState = savedState,
                emptyError = R.string.empty_base,
                lengthError = R.string.length_base_error
            )
        )
    }
}