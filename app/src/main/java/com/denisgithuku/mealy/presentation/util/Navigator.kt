package com.denisgithuku.mealy.presentation.util

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.denisgithuku.mealy.presentation.components.favorites.FavoritesScreen
import com.denisgithuku.mealy.presentation.components.home.HomeScreen
import com.denisgithuku.mealy.presentation.components.meal_details.MealDetailScreen
import com.denisgithuku.mealy.presentation.components.search.SearchScreen
import com.denisgithuku.mealy.presentation.components.settings.SettingsScreen
import com.denisgithuku.mealy.presentation.util.Screen
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalAnimationApi
@InternalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@Composable
fun Navigator(
    navController: NavHostController,
    scaffoldState: ScaffoldState
) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(
                scaffoldState,
                onOpenMeal = { meal ->
                    navController.navigate(Screen.MealDetails.route + "/${meal.idMeal}") {
                            navController.graph.startDestinationRoute?.let { screen_route ->
                                popUpTo(screen_route) {
                                    inclusive = true
                                    saveState = true
                                }
                                restoreState = true
                            }
                    }
                }
            )
        }
        composable(Screen.Search.route) {
            SearchScreen(
                onNavigate = { meal ->
                    navController.navigate(Screen.MealDetails.route + "/${meal.idMeal}") {
                            navController.graph.startDestinationRoute?.let { screen_route ->
                                popUpTo(screen_route) {
                                    inclusive = true
                                    saveState = true
                                }
                                restoreState = true
                            }
                        }
                }
            )
        }
        composable(Screen.Favorites.route) {
            FavoritesScreen()
        }
        composable(Screen.Settings.route) {
            SettingsScreen()
        }
        composable(Screen.MealDetails.route + "/{mealId}") {
            MealDetailScreen(scaffoldState = scaffoldState)
        }
    }
}
