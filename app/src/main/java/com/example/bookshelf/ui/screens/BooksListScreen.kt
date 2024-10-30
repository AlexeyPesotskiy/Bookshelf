package com.example.bookshelf.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.bookshelf.R
import com.example.bookshelf.ui.BooksListUiState
import com.example.bookshelf.ui.ResultType
import com.example.bookshelf.ui.screens.components.SearchField
import kotlinx.coroutines.flow.StateFlow

/**
 * Экран со строкой поиска и списком книг
 *
 * @param state состояние ViewModel
 * @param onSearch функция для отправки запроса
 * @param onSearchQueryChange функция для изменения запроса
 */
@Composable
fun BooksListScreen(
    state: StateFlow<BooksListUiState>,
    onSearch: () -> Unit,
    onSearchQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState = state.collectAsState().value
    Column(
        modifier
            .padding(horizontal = dimensionResource(R.dimen.medium_padding))
            .fillMaxSize()
    ) {
        SearchField(uiState.currentSearchQueryText, onSearch, onSearchQueryChange)

        val screenModifier = Modifier
            .fillMaxSize()
            .padding(vertical = dimensionResource(R.dimen.medium_padding))

//      Открыть экран, соответствующий состоянию BooksListViewModel
        when (uiState.resultType) {
            is ResultType.Loading -> { LoadingScreen(screenModifier) }
            is ResultType.Error -> { ErrorScreen({ onSearch() }, screenModifier) }
            is ResultType.Success -> {
                if (uiState.resultType.books.isNotEmpty() || uiState.isBeforeFirstRequest)
                    BooksList(uiState.resultType.books, screenModifier)
                else
                    NoDataFoundScreen(screenModifier)
            }
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
    val placeholderModifier = Modifier
        .padding(dimensionResource(R.dimen.card_placeholder_size))
    Card(modifier) {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imgSrc)
                .build(),
            contentScale = ContentScale.FillWidth,
            contentDescription = stringResource(R.string.book_cover),
            error = {
                Image(
                    painterResource(R.drawable.ic_broken_image),
                    contentDescription = stringResource(R.string.broken_image),
                    modifier = placeholderModifier
                )
            },
            loading = {
                CircularProgressIndicator(modifier = placeholderModifier)
            },
            modifier = modifier.heightIn(min = dimensionResource(R.dimen.image_size)),
        )
    }
}