package com.app.domain.use_case

import com.app.core.network.GifRepository
import com.app.core.util.DEFAULT_QUERY
import com.app.domain.model.GifUIModel
import com.app.domain.model.toUI

class FetchGifUC(
    private val gifRepository: GifRepository
) {
    suspend operator fun invoke(
        query: String = DEFAULT_QUERY,
        offset: Int = 0,
    ): List<GifUIModel> {
        val response = gifRepository.fetchGifs(
            query = query,
            offset = offset
        )
        return response.toUI()
    }
}