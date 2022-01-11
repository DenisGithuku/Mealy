package com.denisgithuku.mealy.presentation.components.meal_details

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.denisgithuku.mealy.presentation.theme.Orange500
import com.denisgithuku.mealy.presentation.util.Screen
import com.google.accompanist.flowlayout.FlowCrossAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@Composable
fun MealDetailScreen(
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState,
    mealDetailsViewModel: MealDetailsViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state = mealDetailsViewModel.state.value
    val scrollState = rememberScrollState()
    var ingredients: List<String> = emptyList()
    val instructions: MutableList<String> = mutableListOf()
    state.mealDetails.forEach { mealDetail ->
        ingredients = listOf(
            mealDetail.strIngredient1,
            mealDetail.strIngredient2,
            mealDetail.strIngredient3,
            mealDetail.strIngredient4,
            mealDetail.strIngredient5,
            mealDetail.strIngredient6,
            mealDetail.strIngredient7,
            mealDetail.strIngredient8,
            mealDetail.strIngredient9
        )

        mealDetail.strInstructions.split("\r\n").forEach { instruction ->
            instructions.add(instruction)
        }
    }
    mealDetailsViewModel.refreshInstructions(instructions)
    state.mealDetails.forEach { mealDetail ->
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .verticalScroll(
                        state = scrollState,
                        enabled = true
                    )
                    ) {
                Column(
                    modifier = modifier
                        .background(color = Orange500)
                ) {
                    Card(
                        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                        elevation = 0.dp,
                        modifier = Modifier
                            .offset(y = 140.dp)

                    ) {

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(bottom = 60.dp)
                                .background(color = Color.White),
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = mealDetail.strMeal,
                                    textAlign = TextAlign.Center,
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        color = Color.Black.copy(alpha = 0.8f),
                                        fontWeight = FontWeight.SemiBold
                                    ),
                                    modifier = Modifier.padding(top = 80.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(15.dp))
                            Box(
                                modifier = Modifier
                                    .padding(horizontal = 10.dp)
                                    .align(CenterHorizontally)
                                    .clip(
                                        RoundedCornerShape(12.dp)
                                    )
                                    .background(Orange500.copy(alpha = 0.4f))
                            ) {
                                Text(
                                    text = "Ingredients",
                                    style = TextStyle(color = Orange500, fontSize = 14.sp),
                                    modifier = Modifier.padding(8.dp)
                                )
                            }
                            FlowRow(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(4.dp),
                                mainAxisAlignment = MainAxisAlignment.SpaceEvenly,
                                crossAxisAlignment = FlowCrossAxisAlignment.Start,
                                mainAxisSpacing = 4.dp,
                                crossAxisSpacing = 4.dp
                            ) {

                                ingredients.forEach { ingredient ->
                                    if (ingredient.isNotEmpty()) {
                                        IngredientItem(ingredient = ingredient)
                                    }
                                }

                            }
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                            ) {
                                Box(
                                    modifier = Modifier
                                        .padding(horizontal = 10.dp)
                                        .align(CenterHorizontally)
                                        .clip(
                                            RoundedCornerShape(12.dp)
                                        )
                                        .background(Orange500.copy(alpha = 0.4f))
                                ) {
                                    Text(
                                        text = "Cooking instructions",
                                        style = TextStyle(color = Orange500, fontSize = 14.sp),
                                        modifier = Modifier.padding(8.dp)
                                    )
                                }
                                state.mealInstructions.forEach { instruction ->
                                    Text(
                                        text = "${state.mealInstructions.indexOf(instruction) + 1}. $instruction",
                                        modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp)
                                    )
                                }
                            }
                        }

                    }
                }

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = 80.dp)
                        .blur(4.dp, BlurredEdgeTreatment(CircleShape))
                ) {
                    Column(
                        modifier = Modifier
                            .size(140.dp)
                            .clip(CircleShape)
                    ) {
                        GlideImage(
                            imageModel = mealDetail.strMealThumb,
                            contentScale = ContentScale.Crop,
                            alignment = Alignment.Center,
                            loading = {
                                ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                                    val spinnerRef = createRef()
                                    CircularProgressIndicator(
                                        modifier = modifier.constrainAs(spinnerRef) {
                                            top.linkTo(parent.top)
                                            bottom.linkTo(parent.bottom)
                                            start.linkTo(parent.start)
                                            end.linkTo(parent.end)
                                        }
                                    )
                                }
                            }
                        )
                    }
                }


                if (state.isLoading) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        CircularProgressIndicator()
                    }
                }
                if (state.error.isNotEmpty()) {
                    LaunchedEffect(scaffoldState.snackbarHostState) {
                        scaffoldState.snackbarHostState.showSnackbar(
                            "An error occurred",
                            "Retry"
                        )
                    }
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = state.error,
                            style = TextStyle(color = Color.Red),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }

}
