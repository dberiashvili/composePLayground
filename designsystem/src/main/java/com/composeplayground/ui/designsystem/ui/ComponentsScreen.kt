package com.composeplayground.ui.designsystem.ui

import TopBar
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import com.composeplayground.designsystem.R
import com.composeplayground.ui.designsystem.ui.components.ComponentData
import com.composeplayground.ui.designsystem.ui.components.ComponentItem
import com.composeplayground.ui.designsystem.ui.components.ComponentType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComponentsScreen(modifier: Modifier, goBack: () -> Unit) {
    var text by remember {
        mutableStateOf("")
    }

    val items = listOf(
        ComponentData(
            title = "Demo", type = ComponentType.DEMO, onClick = {
                Log.d("clickItem", it.name)
            }
        ),

        ComponentData(
            title = "Layers and Shadows", type = ComponentType.LAYERS_AND_SHADOWS, onClick = {
                Log.d("clickItem", it.name)
            }
        )
    )

    val filteredItems = remember(items, text) {
        if (text.isEmpty()) {
            items
        } else {
            items.filter { item ->
                item.title.contains(text, ignoreCase = true)
            }
        }
    }

    Scaffold(modifier = modifier, topBar = {
        TopBar(
            onSearch = { query ->
                text = query
                items.filter { item ->
                    query.contains(item.title, ignoreCase = true)
                }
            },
            text = text,
            goBack = goBack,
            onCloseButtonClicked = {
                text = ""
            },
            title = stringResource(id = R.string.component_appbar_title),
            searchIsVisible = true
        )
    }, content = { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            if (filteredItems.isEmpty()) {
                items(items) { item ->
                    ComponentItem(item)
                }
            } else {
                items(filteredItems) { item ->
                    ComponentItem(item)
                }
            }

        }
    })
}