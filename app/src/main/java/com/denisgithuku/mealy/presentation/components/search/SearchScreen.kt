package com.denisgithuku.mealy.presentation.components.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.denisgithuku.mealy.presentation.theme.Orange500
import com.denisgithuku.mealy.presentation.util.Screen

@ExperimentalAnimationApi
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {

    val state = searchViewModel.state.value
    var queryString by remember {
        mutableStateOf("")
    }
    Column(
        modifier = modifier.fillMaxSize().padding(bottom = 60.dp)
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            TextField(
                value = queryString,
                onValueChange = {
                    queryString = it
                    searchViewModel.searchMeal(it)
                },
                modifier = modifier.fillMaxWidth(),
                visualTransformation = VisualTransformation.None,
                shape = RoundedCornerShape(25.dp),
                maxLines = 1,
                placeholder = {
                    Text("Search meal by name")
                },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = false,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Search
                ),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black.copy(alpha = 0.6f),
                    placeholderColor = Orange500.copy(alpha = 0.5f),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
        }
        AnimatedVisibility(visible = state.meals.isEmpty()) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier.fillMaxSize()
            ) {
                Text(
                    text = "Results will appear here",
                    style = TextStyle(
                        fontSize = 14.sp
                    ),
                    color = Color.Black.copy(alpha = 0.5f),
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
        LazyColumn(
            modifier = modifier,
            content = {
                items(state.meals) { searchResult ->
                    SearchMealItem(
                        mealItem = searchResult,
                        onClick = { meal ->
                            navController.navigate(Screen.MealDetails.route + "/${meal.idMeal}") {
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
            })
    }
}
