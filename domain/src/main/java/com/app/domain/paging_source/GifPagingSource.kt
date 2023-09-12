package com.app.domain.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.app.core.util.DEFAULT_OFFSET
import com.app.core.util.DEFAULT_QUERY
import com.app.domain.model.GifUIModel
import com.app.domain.use_case.FetchGifUC

class GifPagingSource(
    private val fetchGifUC: FetchGifUC,
    private val query: String = DEFAULT_QUERY
) : PagingSource<Int, GifUIModel>() {
    override fun getRefreshKey(state: PagingState<Int, GifUIModel>): Int {
        return 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GifUIModel> {
        return try {
            val offset = params.key ?: 0
            val gifs = fetchGifUC(
                query = query,
                offset = offset
            )
            LoadResult.Page(
                data = gifs,
                prevKey = null,
                nextKey = offset + DEFAULT_OFFSET
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}