package com.denisgithuku.mealy.presentation.components.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopRow(
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 10.dp, top = 20.dp, end = 10.dp, bottom = 10.dp)
    ) {
        Column(modifier = modifier.weight(0.9f)) {
            Text(
                text = "Find something to eat", style = TextStyle(
                    color = Color.Black.copy(alpha = 0.6f),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
        Card(
            modifier = modifier.weight(0.1f),
            elevation = 16.dp,
            shape = CircleShape
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier
                    .background(color = MaterialTheme.colors.surface)
                    .clickable {
                        // TODO 1 Implement the click
                    }
            ) {
                Icon(
                    modifier = Modifier.padding(4.dp),
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Change app theme"
                )
            }
        }
    }
}
