package com.denisgithuku.mealy.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navOptions
import com.denisgithuku.mealy.presentation.theme.Orange500
import com.denisgithuku.mealy.presentation.util.Screen

@Composable
fun BottomBar(
    currentDestination: NavDestination?,
    screens: List<Screen>,
    onNavigate: (Screen) -> Unit
) {
    Row(
        modifier = Modifier
            .background(color = MaterialTheme.colors.surface)
            .padding(4.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        screens.forEach { screen ->
            BottomBarItem(
                screen = screen,
                isSelected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onSelect = { destination ->
                    onNavigate(destination)
                })
        }
    }
}

@Composable
fun BottomBarItem(
    modifier: Modifier = Modifier,
    screen: Screen,
    isSelected: Boolean,
    onSelect: (Screen) -> Unit
) {

    val backgroundColor = if (isSelected) Orange500.copy(alpha = .7f) else Color.Transparent
    val contentColor = if (isSelected) MaterialTheme.colors.onPrimary else Orange500
    Box(
        modifier = modifier
            .padding(4.dp)
            .background(color = backgroundColor, shape = CircleShape)

    ) {
        Row(
            modifier = Modifier
                .clickable(onClick = { onSelect(screen) })
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Column {
                screen.routeIcon?.let {
                    Icon(
                        imageVector = it,
                        contentDescription = "Screen route",
                        tint = contentColor
                    )
                }
            }
            AnimatedVisibility(visible = isSelected) {
                Text(
                    text = screen.route,
                    style = TextStyle(
                        color = contentColor
                    )
                )
            }
        }
    }
}
