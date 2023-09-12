package com.app.natifetest.ui.gif_screen

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun GifScreen(
    configuration: Configuration,
    title: String,
    url: String,
    webp: String,
    onBackScreen: () -> Unit
) {
    val context = LocalContext.current
    BackHandler {
        onBackScreen()
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            GifScreenToolbar(
                label = title,
                onBackScreen = onBackScreen
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            GifItem(
                orientation = configuration.orientation,
                context = context,
                url = url,
                webp = webp,
                modifier = Modifier
                    .padding(innerPadding)
                    .align(Alignment.TopCenter)
            )
        }
    }
}