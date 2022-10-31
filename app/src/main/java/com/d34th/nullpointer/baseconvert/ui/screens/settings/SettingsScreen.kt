package com.d34th.nullpointer.baseconvert.ui.screens.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.d34th.nullpointer.baseconvert.R
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun SettingsScreen() {
    Scaffold() {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(10.dp)
        ) {
            ChangePrecision(currentPrecision = "10", changePrecision = {})
        }
    }
}

@Composable
private fun ChangePrecision(
    currentPrecision: String,
    changePrecision: (String) -> Unit
) {

    val listPrecision = remember {
        (10..100 step 10).map { it.toString() }
    }

    var isExpandedPrecision by remember {
        mutableStateOf(false)
    }

    val icon = remember(isExpandedPrecision) {
        if (isExpandedPrecision)
            R.drawable.ic_arrow_drop_up
        else
            R.drawable.ic_arrow_drop_down
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Decimal precision")
        Box {
            OutlinedTextField(
                value = currentPrecision,
                onValueChange = changePrecision,
                enabled = false,
                modifier = Modifier
                    .width(100.dp)
                    .clickable {
                        isExpandedPrecision = true
                    },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = null
                    )
                }
            )
            DropdownMenu(expanded = isExpandedPrecision,
                onDismissRequest = { isExpandedPrecision = false }
            ) {

                listPrecision.forEach { precision ->
                    DropdownMenuItem(onClick = {
                        changePrecision(precision)
                        isExpandedPrecision = false
                    }) {
                        Text(text = precision, textAlign = TextAlign.End)
                    }
                }
            }
        }

    }
}