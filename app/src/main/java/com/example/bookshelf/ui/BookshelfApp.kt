package com.example.bookshelf.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookshelf.R
import com.example.bookshelf.ui.screens.BooksListScreen
import com.example.bookshelf.ui.screens.BooksListViewModel

@Composable
fun BookshelfApp() {
    Scaffold(
        topBar = { BookshelfTopAppBar() }
    ) { innerPadding ->
        val booksListViewModel: BooksListViewModel = viewModel(
            factory = BooksListViewModel.Factory
        )

        BooksListScreen(
            state = booksListViewModel.booksListUiState,
            onSearch = booksListViewModel::getBooksList,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookshelfTopAppBar() {
    TopAppBar(
        title = {
            Text(stringResource(R.string.app_name))
        }
    )
}