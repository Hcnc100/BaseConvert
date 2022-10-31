package com.d34th.nullpointer.baseconvert.ui.screens.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.d34th.nullpointer.baseconvert.R
import com.d34th.nullpointer.baseconvert.presentation.SettingsViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun SettingsScreen(
    settingsViewModel: SettingsViewModel = hiltViewModel()
) {
    val currentPrecision by settingsViewModel.currentPrecision.collectAsState()
    SettingsScreen(
        currentPrecision = currentPrecision,
        listPrecision = settingsViewModel.listPrecision,
        changePrecision = settingsViewModel::changePrecision
    )
}


@Composable
private fun SettingsScreen(
    currentPrecision: String,
    listPrecision: List<String>,
    changePrecision: (String) -> Unit
) {
    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = stringResource(R.string.text_explain_precision),
                style = MaterialTheme.typography.caption,
            )
            ChangePrecision(
                listPrecision = listPrecision,
                changePrecision = changePrecision,
                currentPrecision = currentPrecision
            )
        }
    }
}

@Composable
private fun ChangePrecision(
    currentPrecision: String,
    listPrecision: List<String>,
    changePrecision: (String) -> Unit
) {
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
        Text(stringResource(R.string.message_precision))
        Box {
            OutlinedTextField(
                value = currentPrecision,
                enabled = false,
                onValueChange = changePrecision,
                modifier = Modifier
                    .width(150.dp)
                    .clickable { isExpandedPrecision = true },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = null
                    )
                },
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
            )
            DropdownMenu(
                expanded = isExpandedPrecision,
                onDismissRequest = { isExpandedPrecision = false }
            ) {

                listPrecision.forEach { precision ->
                    DropdownMenuItem(
                        onClick = {
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