package com.example.bookshelf.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    val screenModifier = modifier
        .fillMaxSize()
        .padding(horizontal = dimensionResource(R.dimen.medium_padding))

//    Открыть экран, соответствующий состоянию BooksListViewModel
    when (val uiState = state.collectAsState().value) {
        is BooksListUiState.Loading -> { LoadingScreen(screenModifier) }
        is BooksListUiState.Error -> { ErrorScreen(onRetry, screenModifier) }
        is BooksListUiState.Success -> {
            if (uiState.books.isNotEmpty())
                BooksList(uiState.books, screenModifier)
            else
                NoDataFoundScreen(screenModifier)
        }
    }
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