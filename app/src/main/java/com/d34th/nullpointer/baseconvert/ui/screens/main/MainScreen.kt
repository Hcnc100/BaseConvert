package com.d34th.nullpointer.baseconvert.ui.screens.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.d34th.nullpointer.baseconvert.ui.screens.NavGraphs
import com.d34th.nullpointer.baseconvert.ui.screens.share.NavigatorDrawer
import com.d34th.nullpointer.baseconvert.ui.screens.share.ToolbarMenu
import com.d34th.nullpointer.baseconvert.ui.states.MainScreenState
import com.d34th.nullpointer.baseconvert.ui.states.rememberMainScreenState
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun MainScreen(
    mainScreenState: MainScreenState = rememberMainScreenState()
) {
    Scaffold(
        scaffoldState = mainScreenState.scaffoldState,
        topBar = {
            ToolbarMenu(
                title = stringResource(id = mainScreenState.titleNav),
                actionClickMenu = mainScreenState::openDrawer
            )
        },
        drawerContent = {
            NavigatorDrawer(
                closeDrawer = mainScreenState::closeDrawer,
                navController = mainScreenState.navController
            )
        },
    ) { paddingValues ->

        DestinationsNavHost(
            navGraph = NavGraphs.root,
            navController = mainScreenState.navController,
            modifier = Modifier.padding(paddingValues),
        )
    }
}