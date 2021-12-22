package com.denisgithuku.mealy.presentation.components.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.denisgithuku.mealy.presentation.theme.Orange500
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    val bottomSheetScaffoldState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    ModalBottomSheetLayout(
        sheetContent = {
            BottomSheetContent()
        },
        sheetState = bottomSheetScaffoldState,
        sheetBackgroundColor = colors.surface,
        sheetContentColor = colors.onSurface,
        sheetElevation = 16.dp,
        sheetShape = MaterialTheme.shapes.large
    ) {
       val coroutineScope = rememberCoroutineScope()
            Column(modifier = modifier.fillMaxSize()) {
                Button(
                    onClick = {
                        coroutineScope.launch {
                            bottomSheetScaffoldState.show()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Orange500,
                        contentColor = contentColorFor(Orange500)
                    )
                ) {
                    Icon(imageVector = Icons.Default.Settings, contentDescription = "Other tweaks button")
                    Text(text = "Other Tweaks")
                }
            }
        }
    }
