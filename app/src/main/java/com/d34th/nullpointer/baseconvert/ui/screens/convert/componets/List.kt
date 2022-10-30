package com.d34th.nullpointer.baseconvert.ui.screens.convert.componets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ListBaseConvert(
    listBase: List<Int>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(10.dp)
    ) {
        items(items = listBase, key = { it }) {
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Base $it") },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}