package com.example.bookshelf.ui

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
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

//Все 3 возможные состояния (Загружено, Загрузка, Ошибка)
sealed interface ResultType {
    data class Success(val books: List<String>) : ResultType
    data object Loading : ResultType
    data object Error : ResultType
}

data class BooksListUiState (
    val resultType: ResultType,
    val currentSearchQueryText: String = "",
    val isBeforeFirstRequest: Boolean = true,
)

class BooksListViewModel @Inject constructor(private val booksRepository: BooksRepository) : ViewModel() {

    private var _booksListUiState = MutableStateFlow(
        BooksListUiState(
        ResultType.Success(emptyList())
    )
    )
    var booksListUiState = _booksListUiState.asStateFlow()

    //Обновить текст поскового запроса
    fun updateSearchQueryText(userInput: String) {
        _booksListUiState.update {
            it.copy(
                currentSearchQueryText = userInput
            )
        }
    }

    //Получить список ссылок на изображения книг
    fun getBooksList() {
        //Выполнить запрос к API на потоке I/O
        viewModelScope.launch(Dispatchers.IO) {
            _booksListUiState.update {
                it.copy(
                    resultType = ResultType.Loading,
                    isBeforeFirstRequest = false
                )
            }
            _booksListUiState.value =
                _booksListUiState.value.copy(
                    resultType = try {
                        ResultType.Success(
                            booksRepository
                                .getBooks(_booksListUiState.value.currentSearchQueryText)
                        )
                    } catch (ex: Exception) {
                        ResultType.Error
                    },
                    isBeforeFirstRequest = false
                )
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                (this[APPLICATION_KEY] as BookshelfApplication).appComponent.viewModel()
            }
        }
    }
}