package com.app.domain.use_case

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.app.core.util.DEFAULT_OFFSET
import com.app.core.util.DEFAULT_QUERY
import com.app.domain.paging_source.GifPagingSource

class GetPagerUC(
    private val fetchGifUC: FetchGifUC
) {
    operator fun invoke(query: String = DEFAULT_QUERY) = Pager(
        config = PagingConfig(pageSize = DEFAULT_OFFSET),
        pagingSourceFactory = { GifPagingSource(fetchGifUC, query) }
    )
}