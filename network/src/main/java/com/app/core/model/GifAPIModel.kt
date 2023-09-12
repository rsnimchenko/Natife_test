package com.app.core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FetchModel(
    var data: List<GifAPIModel> = listOf()
)

@Serializable
data class GifAPIModel(
    var title: String = "",
    var images: ImagesModel = ImagesModel()
)

@Serializable
data class ImagesModel(
    var original: ImageAPIModel = ImageAPIModel(),
    @SerialName("fixed_height_small")
    var small: ImageAPIModel = ImageAPIModel()
)

@Serializable
data class ImageAPIModel(
    var url: String? = null,
    var webp: String? = null
)