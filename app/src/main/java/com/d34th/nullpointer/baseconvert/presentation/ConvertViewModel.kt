package com.d34th.nullpointer.baseconvert.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d34th.nullpointer.baseconvert.R
import com.d34th.nullpointer.baseconvert.core.utils.ChangeBase
import com.d34th.nullpointer.baseconvert.models.WorkConvert
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class ConvertViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var currentBaseInput: WorkConvert? = null

    val listBaseConvert = (2..36).map {
        WorkConvert(base = it, savedStateHandle = savedStateHandle)
    }


    val basicBase = listBaseConvert.filter {
        it.base in sequenceOf(2, 4, 8, 10, 16)
    }

    fun triggerConvert(newInput: String, baseInput: WorkConvert) {
        this.currentBaseInput = baseInput
        listBaseConvert.forEach { currentWorked ->
            currentWorked.jobConvert?.cancel()
            if (currentWorked == this.currentBaseInput) {
                baseInput.changeValue(newInput)
            } else {
                if (ChangeBase.validate(newInput, baseInput.base)) {
                    currentWorked.jobConvert = viewModelScope.launch {
                        val result = withContext(Dispatchers.IO) {
                            ChangeBase.baseToBase(
                                baseTo = currentWorked.base,
                                numberString = newInput,
                                baseFrom = baseInput.base
                            )
                        }
                        currentWorked.changeValue(result)
                    }
                } else {
                    currentWorked.changeValue("")
                    if (!baseInput.hasError)
                        baseInput.setError(R.string.error_value_invalid)
                }
            }

        }
    }

}