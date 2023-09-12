package com.app.natifetest.ui.list_screen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.app.domain.model.GifUIModel
import com.app.natifetest.ui.LoadingState
import kotlinx.coroutines.flow.StateFlow

@Composable
fun ListScreenBody(
    configuration: Configuration,
    uiStateFlow: StateFlow<ListScreenUiState>,
    toGifScreen: (title: String, url: String, webp: String) -> Unit,
    updateLoadingState: (LoadingState) -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by uiStateFlow.collectAsState()
    val gifs = uiState.gifsFlow?.collectAsLazyPagingItems()
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.surface)
    ) {
        GitUsersGrid(
            orientation = configuration.orientation,
            gifs = gifs,
            toGifScreen = toGifScreen,
            updateLoadingState = updateLoadingState
        )
        LoadingStateScreens(loadingState = uiState.loadingState) { gifs?.refresh() }
    }
}

@Composable
fun LoadingStateScreens(
    loadingState: LoadingState,
    errorCallback: () -> Unit
) {
    when (loadingState) {
        LoadingState.Error -> {
            LoadFailedScreen { errorCallback() }
        }

        LoadingState.Loading -> {
            LoadingScreen()
        }

        else -> {}
    }
}

@Composable
fun GitUsersGrid(
    orientation: Int,
    gifs: LazyPagingItems<GifUIModel>?,
    toGifScreen: (title: String, url: String, webp: String) -> Unit,
    updateLoadingState: (LoadingState) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    LazyVerticalGrid(
        columns = GridCells.Fixed(if (orientation == Configuration.ORIENTATION_LANDSCAPE) 3 else 2),
        modifier = modifier.fillMaxSize()
    ) {
        items(gifs?.itemCount ?: 0) {
            GifItem(
                context = context,
                onItemClick = {
                    toGifScreen(
                        gifs?.get(it)?.title ?: "",
                        gifs?.get(it)?.original?.url ?: "",
                        gifs?.get(it)?.original?.webp ?: ""
                    )
                },
                title = gifs?.get(it)?.title ?: "",
                url = gifs?.get(it)?.small?.url ?: "",
                webp = gifs?.get(it)?.small?.webp ?: ""
            )
        }

        loadingRefreshState(
            state = gifs?.loadState?.refresh,
            updateLoadingState = updateLoadingState
        )
        loadingAppendState(state = gifs?.loadState?.append) { gifs?.refresh() }
    }
}