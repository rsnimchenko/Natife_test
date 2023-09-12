package com.app.natifetest.ui.list_screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.navigation.koinNavViewModel

@Composable
fun ListScreen(
    configuration: Configuration,
    toGifScreen: (title: String, url: String, webp: String) -> Unit,
    vm: ListScreenViewModel = koinNavViewModel()
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            ListScreenToolbar(
                uiStateFlow = vm.uiState,
                updateGifFlow = vm::updateGifFlow,
                updateTextSearchField = vm::updateTextSearchField
            )
        }
    ) { innerPadding ->
        ListScreenBody(
            configuration = configuration,
            uiStateFlow = vm.uiState,
            toGifScreen = toGifScreen,
            updateLoadingState = vm::updateLoadingState,
            modifier = Modifier.padding(innerPadding)
        )
    }
}