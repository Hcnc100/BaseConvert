package com.d34th.nullpointer.baseconvert.ui.screens.share

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.d34th.nullpointer.baseconvert.R

@Composable
fun ToolbarMenu(
    title: String,
    actionClickMenu: () -> Unit
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.primary,
        title = { Text(text = title, color = Color.White) },
        navigationIcon = { IconMenu(actionClickMenu) }
    )
}

@Composable
private fun IconMenu(actionClickMenu: () -> Unit) {
    IconButton(onClick = actionClickMenu) {
        Icon(
            painter = painterResource(id = R.drawable.ic_menu),
            contentDescription = stringResource(R.string.description_drawer_menu),
            tint = Color.White
        )
    }
}
