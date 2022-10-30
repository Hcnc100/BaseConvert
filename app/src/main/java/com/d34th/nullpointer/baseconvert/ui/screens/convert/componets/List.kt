package com.d34th.nullpointer.baseconvert.ui.screens.convert.componets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.d34th.nullpointer.baseconvert.models.WorkConvert
import com.d34th.nullpointer.baseconvert.presentation.EditableTextSavable

@Composable
fun ListBaseConvert(
    listBase: List<WorkConvert>,
    modifier: Modifier = Modifier,
    triggerBaseConvert: (String, WorkConvert) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(10.dp)
    ) {
        items(items = listBase, key = { it.base }) {
            EditableTextSavable(workConvert = it, triggerBaseConvert = triggerBaseConvert)
        }
    }
}