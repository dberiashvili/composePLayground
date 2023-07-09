import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalFocusManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    onSearch: (String) -> Unit,
    text: String,
    goBack: () -> Unit,
    onCloseButtonClicked: () -> Unit = {},
    title: String,
    searchIsVisible: Boolean = false,
    focusManager: FocusManager = LocalFocusManager.current
) {
    var isSearchVisible: Boolean by remember { mutableStateOf(false) }
    var searchText: String by remember { mutableStateOf(text) }

    CenterAlignedTopAppBar(title = {
        Text(text = title)
    }, actions = {
        if (searchIsVisible) {
            IconButton(onClick = {
                isSearchVisible = !isSearchVisible
                if (!isSearchVisible) {
                    focusManager.clearFocus()
                    searchText = ""
                }
            }) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "Search icon")
            }
        }
    }, navigationIcon = {
        IconButton(onClick = goBack) {
            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Navigation icon")
        }
    })

    AnimatedVisibility(visible = isSearchVisible) {
        Row(modifier = Modifier.padding(horizontal = 16.dp)) {
            TextField(value = searchText,
                onValueChange = { newText ->
                    searchText = newText
                    onSearch(newText)
                },
                placeholder = { Text(text = "Search") },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = {
                    onSearch(searchText)
                    focusManager.clearFocus()
                }),
                trailingIcon = {
                    IconButton(onClick = {
                        onCloseButtonClicked()
                        if (searchText.isEmpty()) {
                            isSearchVisible = false
                        } else {
                            searchText = ""
                        }

                    }) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "Clear search icon",
                            tint = Color.Gray
                        )
                    }
                },
                modifier = Modifier.padding(top = 16.dp).fillMaxWidth().border(1.dp, Color.LightGray, CircleShape),
                shape = CircleShape,
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )
        }
    }
}
