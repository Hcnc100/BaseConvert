package com.d34th.nullpointer.baseconvert.ui.states

import android.content.Context
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.d34th.nullpointer.baseconvert.R
import com.d34th.nullpointer.baseconvert.ui.navigation.MainDestinations
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainScreenState(
    context: Context,
    val scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    val navController: NavHostController
) : SimpleScreenState(context, scaffoldState) {

    var titleNav by mutableStateOf(R.string.app_name)
        private set

    init {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            titleNav = MainDestinations.getLabel(destination.route)
        }
    }

    fun openDrawer() {
        scope.launch {
            scaffoldState.drawerState.open()
        }
    }

    fun closeDrawer() {
        scope.launch {
            scaffoldState.drawerState.close()
        }
    }
}

@Composable
fun rememberMainScreenState(
    context: Context = LocalContext.current,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
) = remember(scaffoldState, navController, coroutineScope) {
    MainScreenState(
        context = context,
        scope = coroutineScope,
        navController = navController,
        scaffoldState = scaffoldState
    )
}