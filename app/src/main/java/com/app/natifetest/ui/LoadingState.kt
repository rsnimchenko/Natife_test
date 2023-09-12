package com.app.natifetest.ui

sealed class LoadingState {
    object Init : LoadingState()
    object Loading : LoadingState()
    object Error : LoadingState()
}
