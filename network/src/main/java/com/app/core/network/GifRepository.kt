package com.app.core.network

import com.app.core.model.FetchModel
import com.app.core.model.GifAPIModel
import com.app.core.util.API_KEY
import com.app.core.util.BASE_URL
import com.app.core.util.DEFAULT_LANG
import com.app.core.util.DEFAULT_OFFSET
import com.app.core.util.DEFAULT_QUERY
import com.app.core.util.DEFAULT_RATING
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url

class GifRepository(private val client: HttpClient) {
    suspend fun fetchGifs(
        query: String = DEFAULT_QUERY,
        limit: Int = DEFAULT_OFFSET,
        offset: Int = 0,
        rating: String = DEFAULT_RATING,
        lang: String = DEFAULT_LANG
    ): List<GifAPIModel> {
        val response = client.get(
            HttpRequestBuilder().apply {
                url(BASE_URL)
                parameter("api_key", API_KEY)
                parameter("q", query)
                parameter("limit", limit)
                parameter("offset", offset)
                parameter("rating", rating)
                parameter("lang", lang)
                parameter("bundle", "messaging_non_clips")
            }
        )
        if (response.status.value == 200) {
            val fetchModel = response.body<FetchModel>()
            return fetchModel.data
        } else throw Exception()
    }
}