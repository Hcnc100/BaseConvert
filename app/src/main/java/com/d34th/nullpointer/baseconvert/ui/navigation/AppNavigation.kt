package com.d34th.nullpointer.baseconvert.ui.navigation

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavHostController

/** Navigation owned by the activity, outside the drawer-based main shell. */
val LocalRootNavController = staticCompositionLocalOf<NavHostController> {
    error("The root navigation controller was not provided")
}
