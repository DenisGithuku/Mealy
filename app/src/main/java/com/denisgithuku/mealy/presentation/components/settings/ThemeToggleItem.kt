package com.denisgithuku.mealy.presentation.components.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun ThemeToggleItem(
    modifier: Modifier = Modifier,
    isInDarkMode: Boolean,
    onChangeTheme: (Boolean) -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Switch(checked = isInDarkMode, onCheckedChange = {
                onChangeTheme(isInDarkMode)
            })
        }
    }
    Column {
        Text(
            text = "Toggle app theme",
            style = TextStyle(color = MaterialTheme.colors.onSurface, fontSize = 14.sp)
        )
    }
}

@Preview
@Composable
fun ThemeToggleItemPrev() {
    ThemeToggleItem(isInDarkMode = false, onChangeTheme = {})
}
