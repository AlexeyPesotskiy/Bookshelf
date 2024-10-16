package com.example.bookshelf.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookshelf.BookshelfApplication
import com.example.bookshelf.data.BooksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

//Все 3 возможные состояния (Загружено, Загрузка, Ошибка)
sealed interface BooksListUiState {
    data class Success(val books: List<String>) : BooksListUiState
    data object Loading : BooksListUiState
    data object Error : BooksListUiState
}

class BooksListViewModel(private val booksRepository: BooksRepository) : ViewModel() {

    private var _booksListUiState = MutableStateFlow<BooksListUiState>(BooksListUiState.Loading)
    var booksListUiState = _booksListUiState.asStateFlow()

    init {
        getBooksList("Saint Petersburg")
    }

    fun getBooksList(userQuery: String) {
        //Выполнить запрос к сети на потоке I/O
        viewModelScope.launch(Dispatchers.IO) {
            _booksListUiState.value = BooksListUiState.Loading
            _booksListUiState.value = try {
                BooksListUiState.Success(booksRepository.getBooks(userQuery))
            } catch (ex: Exception) {
                BooksListUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                BooksListViewModel(
                    booksRepository = (this[APPLICATION_KEY] as BookshelfApplication)
                        .container.booksRepository
                )
            }
        }
    }
}