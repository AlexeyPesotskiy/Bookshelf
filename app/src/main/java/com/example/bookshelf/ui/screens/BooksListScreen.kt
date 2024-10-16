package com.example.bookshelf.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookshelf.R
import kotlinx.coroutines.flow.StateFlow

/**
 * Экран со списком книг
 *
 * @param state состояние [BooksListViewModel]
 * @param onRetry лямбда-функция для обновления списка книг
 */
@Composable
fun BooksListScreen(
    state: StateFlow<BooksListUiState>,
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var searchQueryText by remember { mutableStateOf("") }
    Column(
        modifier
            .padding(horizontal = dimensionResource(R.dimen.medium_padding))
            .fillMaxSize()
    ) {
        BooksSearchField(searchQueryText, onSearch) {
            searchQueryText = it
        }

        val screenModifier = Modifier
            .fillMaxSize()
            .padding(vertical = dimensionResource(R.dimen.medium_padding))

//      Открыть экран, соответствующий состоянию BooksListViewModel
        when (val uiState = state.collectAsState().value) {
            is BooksListUiState.Loading -> { LoadingScreen(screenModifier) }
            is BooksListUiState.Error -> { ErrorScreen({ onSearch(searchQueryText) }, screenModifier) }
            is BooksListUiState.Success -> {
                if (uiState.books.isNotEmpty())
                    BooksList(uiState.books, screenModifier)
                else
                    NoDataFoundScreen(screenModifier)
            }
        }
    }
}

/**
 * Поле ввода поискового запроса
 *
 * @param searchText текст запроса
 * @param onSearch лямбда-функция отправки поискового запроса
 * @param onQueryChange callback при изменении текста запроса пользователем
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BooksSearchField(
    searchText: String,
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier,
    onQueryChange: (String) -> Unit
) {
    SearchBar(
        inputField = {
            SearchBarDefaults.InputField(
                query = searchText,
                onQueryChange = { onQueryChange(it) },
                onSearch = { onSearch(it) },
                expanded = false,
                onExpandedChange = {}
            )
        },
        expanded = false,
        onExpandedChange = {},
        windowInsets = WindowInsets(0),
        modifier = modifier
    ) {}
}

@Composable
fun BooksList(
    books: List<String>,
    modifier: Modifier
) {
    LazyVerticalStaggeredGrid(
        verticalItemSpacing = dimensionResource(R.dimen.list_item_spacing),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.list_item_spacing)),
        columns = StaggeredGridCells.Adaptive(dimensionResource(R.dimen.grid_column_width)),
        modifier = modifier
    ) {
        items(
            items = books
        ) {
            BooksCard(
                imgSrc = it,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun BooksCard(
    imgSrc: String,
    modifier: Modifier = Modifier
) {
    Card(modifier) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imgSrc)
                .build(),
            contentScale = ContentScale.FillWidth,
            contentDescription = stringResource(R.string.book_cover),
            error = painterResource(R.drawable.ic_broken_image),
            placeholder = painterResource(R.drawable.loading_img),
            modifier = modifier
        )
    }
}