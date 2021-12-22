package com.denisgithuku.mealy.presentation.components.home

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.denisgithuku.mealy.presentation.util.Screen

@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    scaffoldState: ScaffoldState,
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val homeScreenState = homeViewModel.state.value
    val context = LocalContext.current
    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(bottom = 60.dp),
        ) {
            TopRow()
            if (!homeScreenState.isLoading && homeScreenState.error.isEmpty()) {
                Column {
                    Text(
                        text = "Categories",
                        style = TextStyle(
                            fontSize = 12.sp
                        ),
                        color = Color.Black.copy(alpha = 0.5f),
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }
            LazyRow {
                items(homeScreenState.mealCategories) { categoryItem ->
                    MealCategoryItem(
                        imageUrl = categoryItem.strCategoryThumb,
                        mealCategory = categoryItem.strCategory,
                        onClick = {
                            homeViewModel.changeSelectedMealCategory(it)
                            homeViewModel.getMealsInCategory(it)
                        }
                    )
                }
            }
            if (!homeScreenState.isLoading && homeScreenState.error.isEmpty()) {
                Text(
                    text = homeViewModel.selectedMealCategory.value,
                    style = TextStyle(
                        fontSize = 10.sp
                    ),
                    color = Color.Black.copy(alpha = 0.5f),
                    modifier = Modifier.padding(10.dp)
                )
            }
            LazyColumn {
                items(homeScreenState.mealInEachCategory) { mealInEachCategory ->
                    MealItem(mealItem = mealInEachCategory, onClick = { meal ->
                        navController.navigate(Screen.MealDetails.route + "/${meal.idMeal}"){
                            navOptions {
                                popUpTo(Screen.Home.route) {
                                    inclusive = true
                                    saveState = true
                                }
                                restoreState = true
                            }
                        }
                    })
                }
            }

            if (homeScreenState.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Center,
                ) {
                    CircularProgressIndicator()
                }
            }
            if (homeScreenState.error.isNotEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Center
                ) {
                    Text(
                        text = homeScreenState.error,
                        color = Color.Red,
                        textAlign = TextAlign.Center
                    )
                }
                LaunchedEffect(scaffoldState.snackbarHostState) {
                    val result = scaffoldState.snackbarHostState.showSnackbar(
                        "An error occurred",
                        "Retry"
                    )
                    when (result) {
                        SnackbarResult.ActionPerformed -> {
                            Toast.makeText(context, "Retrying", Toast.LENGTH_SHORT).show()
                            homeViewModel.retryFetchingData()
                        }
                        SnackbarResult.Dismissed -> {
                            Toast.makeText(context, "Could not reach server", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}
