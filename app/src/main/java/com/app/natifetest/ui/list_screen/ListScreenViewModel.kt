package com.app.natifetest.ui.list_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.app.domain.model.GifUIModel
import com.app.domain.use_case.GetPagerUC
import com.app.natifetest.ui.LoadingState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val listVMModule = module {
    viewModel { ListScreenViewModel(get()) }
}

class ListScreenViewModel(
    private val getPagerUC: GetPagerUC
) : ViewModel() {

    private val _uiState = MutableStateFlow(ListScreenUiState())
    val uiState: StateFlow<ListScreenUiState> = _uiState

    init {
        _uiState.update {
            it.copy(gifsFlow = getPagerUC().flow.cachedIn(viewModelScope))
        }
    }

    fun updateLoadingState(state: LoadingState) {
        _uiState.update {
            it.copy(loadingState = state)
        }
    }

    fun updateTextSearchField(text: String) {
        _uiState.update {
            it.copy(textSearchField = text)
        }
    }

    fun updateGifFlow() {
        _uiState.update {
            it.copy(
                gifsFlow = getPagerUC(it.textSearchField).flow.cachedIn(viewModelScope),
                textSearchField = ""
            )
        }
    }
}

data class ListScreenUiState(
    val loadingState: LoadingState = LoadingState.Init,
    val textSearchField: String = "",
    val gifsFlow: Flow<PagingData<GifUIModel>>? = null
)