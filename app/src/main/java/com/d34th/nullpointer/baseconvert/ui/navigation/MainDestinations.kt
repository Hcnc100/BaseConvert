package com.d34th.nullpointer.baseconvert.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.d34th.nullpointer.baseconvert.R
import com.d34th.nullpointer.baseconvert.ui.screens.destinations.ConvertScreenDestination
import com.d34th.nullpointer.baseconvert.ui.screens.destinations.DirectionDestination
import com.d34th.nullpointer.baseconvert.ui.screens.destinations.SettingsScreenDestination

enum class MainDestinations(
    @StringRes val label: Int,
    @DrawableRes val icon: Int,
    val destinations: DirectionDestination
) {
    ConvertBase(
        label = R.string.title_change_base,
        icon = R.drawable.ic_calculate,
        destinations = ConvertScreenDestination
    ),

    ProjectsScreen(
        label = R.string.title_settings,
        icon = R.drawable.ic_settings,
        destinations = SettingsScreenDestination
    );


    companion object {
        fun isHomeRoute(route: String?): Boolean {
            if (route == null) return false
            return values().find { it.destinations.route == route } != null
        }

        fun getLabel(route: String?): Int {
            if (route == null) return R.string.app_name
            return values().find { it.destinations.route == route }?.label ?: R.string.app_name
        }
    }
}