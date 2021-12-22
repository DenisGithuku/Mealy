package com.denisgithuku.mealy.presentation.components.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BottomSheetContent(
    modifier: Modifier = Modifier
) {

    var isSystemInDarkByMode by remember {
        mutableStateOf(false)
    }
    Column(modifier = modifier
        .fillMaxSize()
        .padding(10.dp)) {
        ThemeToggleItem(isInDarkMode = isSystemInDarkByMode, onChangeTheme = {
                isSystemInDarkByMode = !isSystemInDarkByMode
        })
        BottomSheetItem(imageVector = Icons.Default.Share, contentDescription = "Share this app", text = "Share this")
        BottomSheetItem(imageVector = Icons.Default.Edit, contentDescription = "Edit", text = "Edit content")
    }
}

@Composable
fun BottomSheetItem(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    contentDescription: String,
    text: String
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(imageVector = imageVector, contentDescription = contentDescription)
        Spacer(modifier = modifier.width(8.dp))
        Text(text = text)
    }
}

@Preview
@Composable
fun BottomSheetItemPrev() {
    BottomSheetItem(imageVector = Icons.Default.Share, contentDescription = "content", text = "Testing")
}
