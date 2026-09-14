package com.d34th.nullpointer.baseconvert.ui.activitys

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.compose.ui.res.stringResource
import com.d34th.nullpointer.baseconvert.ui.screens.main.MainScreen
import com.d34th.nullpointer.baseconvert.ui.screens.explanation.ExplanationDetailScreen
import com.d34th.nullpointer.baseconvert.ui.screens.share.ToolbarBack
import com.d34th.nullpointer.baseconvert.ui.navigation.LocalRootNavController
import com.d34th.nullpointer.baseconvert.ui.theme.BaseConvertTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            BaseConvertTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

private const val MAIN_ROUTE = "main"
private const val EXPLANATION_DETAIL_ROUTE = "explanation-detail/{number}/{fromBase}/{toBase}"

@androidx.compose.runtime.Composable
private fun AppNavigation() {
    val rootNavController = rememberNavController()
    CompositionLocalProvider(LocalRootNavController provides rootNavController) {
        NavHost(rootNavController, startDestination = MAIN_ROUTE) {
            composable(MAIN_ROUTE) { MainScreen() }
            composable(
                route = EXPLANATION_DETAIL_ROUTE,
                arguments = listOf(
                    navArgument("number") { type = NavType.StringType },
                    navArgument("fromBase") { type = NavType.IntType },
                    navArgument("toBase") { type = NavType.IntType }
                )
            ) { entry ->
                val number = requireNotNull(entry.arguments?.getString("number"))
                val fromBase = entry.arguments?.getInt("fromBase") ?: 10
                val toBase = entry.arguments?.getInt("toBase") ?: 2
                Scaffold(
                    topBar = {
                        ToolbarBack(
                            title = stringResource(com.d34th.nullpointer.baseconvert.R.string.step_by_step_title),
                            onBack = rootNavController::popBackStack
                        )
                    }
                ) { paddingValues ->
                    androidx.compose.foundation.layout.Box(Modifier.padding(paddingValues)) {
                        ExplanationDetailScreen(number, fromBase, toBase)
                    }
                }
            }
        }
    }
}
