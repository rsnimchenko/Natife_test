package com.app.natifetest.ui.gif_screen

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder

@Composable
fun GifScreenToolbar(
    label: String,
    onBackScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colors.primary
            )
            .height(50.dp)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "Back",
            tint = MaterialTheme.colors.secondary,
            modifier = Modifier.clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = { onBackScreen() }
            )
        )
        Text(
            text = label,
            style = MaterialTheme.typography.h2,
            color = MaterialTheme.colors.onPrimary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun GifItem(
    orientation: Int,
    context: Context,
    url: String,
    webp: String,
    modifier: Modifier = Modifier
) {
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()
    val cardModifier = if (orientation == Configuration.ORIENTATION_PORTRAIT)
        modifier.fillMaxWidth() else modifier.fillMaxHeight()
    val gifModifier = if (orientation == Configuration.ORIENTATION_PORTRAIT)
        Modifier.fillMaxWidth() else Modifier.fillMaxHeight()
    val contentScale = if (orientation == Configuration.ORIENTATION_PORTRAIT)
        ContentScale.FillWidth else ContentScale.FillHeight

    Card(
        modifier = cardModifier
            .padding(8.dp),
        elevation = 4.dp,
    ) {
        AsyncImage(
            model = if (Build.VERSION.SDK_INT >= 28) webp else url,
            contentDescription = "big_gif",
            contentScale = contentScale,
            imageLoader = imageLoader,
            modifier = gifModifier
                .padding(8.dp)
                .clip(MaterialTheme.shapes.medium)
        )
    }

}