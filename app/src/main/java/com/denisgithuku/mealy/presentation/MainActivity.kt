package com.denisgithuku.mealy.presentation

import android.app.Application
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.denisgithuku.mealy.presentation.components.BottomBar
import com.denisgithuku.mealy.presentation.components.meal_details.CustomTopAppBar
import com.denisgithuku.mealy.presentation.theme.MealyTheme
import com.denisgithuku.mealy.presentation.util.Navigator
import com.denisgithuku.mealy.presentation.util.Screen
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.InternalCoroutinesApi

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @InternalCoroutinesApi
    @ExperimentalMaterialApi
    @ExperimentalMaterial3Api
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MealyTheme {

                val screens = listOf(
                    Screen.Home,
                    Screen.Search,
                    Screen.Favorites
                )

                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                val context = LocalContext.current
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination?.route?.substringBefore("/")
                Scaffold(
                    scaffoldState = scaffoldState,
                    topBar = {
                        if (currentDestination == Screen.MealDetails.route) {
                            CustomTopAppBar(
                                onNavigateUp = {
                                    navController.navigateUp()
                                }
                            )
                        }
                    },
                    bottomBar = {
                        if (currentDestination != Screen.MealDetails.route) {
                            BottomBar(
                                currentDestination = navBackStackEntry?.destination,
                                screens = screens,
                                onNavigate = { destination ->
                                    navController.navigate(destination.route) {
                                            navController.graph.startDestinationRoute?.let { screen_route ->
                                                popUpTo(screen_route) {
                                                    saveState = true
                                                }
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                }
                            )
                        }
                    }
                ) {
                    Navigator(navController = navController, scaffoldState = scaffoldState)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MealyTheme {
        Greeting("Android")
    }
}

@HiltAndroidApp
class MealyApplication : Application()
