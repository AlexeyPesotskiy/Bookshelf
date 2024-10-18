package com.example.bookshelf.ui.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.bookshelf.R

/**
 * Поле ввода поискового запроса
 *
 * @param searchText текст запроса
 * @param onSearch функция для отправки запроса
 * @param onQueryChange функция для изменения запроса
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchField(
    searchText: String,
    onSearch: () -> Unit,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var isError by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

    val onSearchWithCheck = {
        if (searchText.isNotBlank()) {
            keyboardController?.hide()
            onSearch()
        }
        else
            isError = true
    }

    SearchBar(
        inputField = {
            SearchBarDefaults.InputField(
                query = searchText,
                onQueryChange = {
                    onQueryChange(it)
                    isError = false
                },
                onSearch = { onSearchWithCheck() },
                expanded = false,
                onExpandedChange = {},
                trailingIcon = {
                    if (isError)
                        Icon(
                            imageVector = Icons.Sharp.Info,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.error
                        )
                    else
                        IconButton(onClick = onSearchWithCheck) {
                            Image(painterResource(R.drawable.ic_search), "")
                        }
                },
                placeholder = {
                    if (isError)
                        Text(
                            text = stringResource(R.string.search_placeholder_error),
                            color = MaterialTheme.colorScheme.error.copy(alpha = 0.7f)
                        )
                    else
                        Text(stringResource(R.string.search_placeholder))
                }
            )
        },
        expanded = false,
        onExpandedChange = {},
        windowInsets = WindowInsets(0),
        modifier = modifier.fillMaxWidth()
    ) {}
}