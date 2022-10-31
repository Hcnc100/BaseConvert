package com.d34th.nullpointer.baseconvert.ui.states

import android.content.Context
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

class SettingsScreenState(
    context: Context,
    scaffoldState: ScaffoldState
) : SimpleScreenState(context, scaffoldState) {
}

@Composable
fun rememberSettingsScreenState(
    context: Context,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
) = remember {
    SettingsScreenState(context, scaffoldState)
}