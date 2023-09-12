package com.app.natifetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.app.domain.model.GifUIModel
import com.app.domain.use_case.GetPagerUC
import com.app.natifetest.ui.theme.NatifeTestTheme
import kotlinx.coroutines.delay
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    val getPagerUC: GetPagerUC by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NatifeTestTheme {
                val pager = remember {
                    mutableStateOf(getPagerUC())
                }
                LaunchedEffect(key1 = Unit) {
                    delay(10000)
                    pager.value = getPagerUC("dog")
                }

                val gifs = pager.value.flow.collectAsLazyPagingItems()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    GifList(gifs)
                }
            }
        }
    }
}

@Composable
fun GifList(
    gifs: LazyPagingItems<GifUIModel>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        if (gifs.loadState.refresh == LoadState.Loading) {
            item {
                Text(
                    text = "Waiting for items to load",
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
            }
        }

        items(gifs.itemCount) {
            Text(
                text = gifs[it]?.title ?: "",
                modifier = modifier,
                style = MaterialTheme.typography.h3
            )
        }

        if (gifs.loadState.append == LoadState.Loading) {
            item {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
            }
        }
    }
}