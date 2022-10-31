package com.d34th.nullpointer.baseconvert.presentation

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
) : ViewModel() {

    private var currentBaseInput: WorkConvert? = null

    val listBaseConvert = (2..16).map {
        WorkConvert(base = it)
    }


    val basicBase = listBaseConvert.filter {
        it.base in sequenceOf(2, 4, 8, 10, 16)
    }

    fun triggerConvert(newInput: String, baseInput: WorkConvert) {
        this.currentBaseInput = baseInput
        listBaseConvert.forEach {
            it.jobConvert?.cancel()
            if (it == this.currentBaseInput) {
                baseInput.propertyBase.changeValue(newInput)
            } else {
                if (ChangeBase.validate(newInput, baseInput.base)) {
                    it.jobConvert = viewModelScope.launch {
                        val result = withContext(Dispatchers.IO) {
                            ChangeBase.baseToBase(
                                baseTo = it.base,
                                numberString = newInput,
                                baseFrom = baseInput.base
                            )
                        }
                        it.propertyBase.changeValue(result)
                    }
                } else {
                    baseInput.propertyBase.setAnotherError(R.string.error_value_invalid)
                    it.propertyBase.changeValue("")
                }
            }

        }
    }

}