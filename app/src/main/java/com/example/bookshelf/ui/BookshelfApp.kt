package com.example.bookshelf.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookshelf.R
import com.example.bookshelf.ui.screens.BooksListScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookshelfApp() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            if(LocalConfiguration.current.orientation != Configuration.ORIENTATION_LANDSCAPE)
                BookshelfTopAppBar(scrollBehavior)
        }
    ) { innerPadding ->
        val booksListViewModel: BooksListViewModel = viewModel(
            factory = BooksListViewModel.Factory
        )

        BooksListScreen(
            state = booksListViewModel.booksListUiState,
            onSearch = booksListViewModel::getBooksList,
            onSearchQueryChange = booksListViewModel::updateSearchQueryText,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookshelfTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior
) {
    TopAppBar(
        title = {
            Text(stringResource(R.string.app_name))
        },
        scrollBehavior = scrollBehavior,
        //Установить цвет верхней панели после скролла, как цвет фона
        colors = TopAppBarDefaults.topAppBarColors(
            scrolledContainerColor = MaterialTheme.colorScheme.surface
        )
    )
}