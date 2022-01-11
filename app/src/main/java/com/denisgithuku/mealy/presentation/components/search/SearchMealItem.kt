package com.denisgithuku.mealy.presentation.components.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.denisgithuku.mealy.domain.model.MealInCategory
import com.denisgithuku.mealy.domain.model.MealInSearch
import com.denisgithuku.mealy.domain.model.MealPreparation
import com.denisgithuku.mealy.presentation.util.Screen
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun SearchMealItem(
    modifier: Modifier = Modifier,
    mealItem: MealInSearch,
    onClick: (MealInSearch) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.surface)
            .padding(8.dp)
            .clickable {
                onClick(mealItem)
            },
    ) {
        Column(
            modifier = modifier
                .padding(8.dp)
                .size(60.dp),
        ) {
            Card(
                modifier = modifier.fillMaxSize(),
                shape = RoundedCornerShape(16.dp),
                elevation = 0.dp,
            ) {
                Column(modifier = modifier.fillMaxSize()) {
                    GlideImage(
                        imageModel = mealItem.strMealThumb,
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
        }
        Column {
            Text(
                text = mealItem.strMeal,
                style = TextStyle(
                    color = Color.Black.copy(alpha = 0.7f),
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}
