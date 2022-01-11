package com.denisgithuku.mealy.presentation.components.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun MealCategoryItem(
    modifier: Modifier = Modifier,
    imageUrl: String,
    mealCategory: String,
    onClick: (String) -> Unit
) {
    Card(
        elevation = 16.dp,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .size(100.dp)
            .padding(4.dp)
            .clickable {
                onClick(mealCategory)
            }
    ) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            GlideImage(
                imageModel = imageUrl,
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                loading = {
                    ConstraintLayout(modifier = modifier.fillMaxSize()) {
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
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.7f)
                            ),
                            startY = 100f
                        )
                    )
            )
            Text(
                text = mealCategory, style = TextStyle(
                    color = Color.White,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                ),
                modifier = modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 4.dp)
            )
        }
    }
}
