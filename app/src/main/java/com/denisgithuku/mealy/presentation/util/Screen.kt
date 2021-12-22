package com.denisgithuku.mealy.presentation.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val routeIcon: ImageVector?) {
    object Home: Screen("Home", Icons.Outlined.Home)
    object Search: Screen("Search", Icons.Outlined.Search)
    object Settings: Screen("Settings", Icons.Outlined.Settings)
    object Favorites: Screen("Favorites", Icons.Outlined.Favorite)
    object MealDetails: Screen("Details", null)
}
