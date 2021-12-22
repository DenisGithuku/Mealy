package com.denisgithuku.mealy.presentation.components.meal_details

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.denisgithuku.mealy.presentation.theme.Orange500

@Composable
fun IngredientItem(
    ingredient: String
) {

    Box(
        modifier = Modifier
            .padding(2.dp)
            .border(
                width = 0.7.dp,
                color = Orange500.copy(alpha = 0.7f),
                shape = RoundedCornerShape(50.dp)
            )
    ) {
        Text(text = ingredient, modifier = Modifier.padding(8.dp), color = Orange500)
    }

}
