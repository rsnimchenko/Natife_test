package com.app.domain.model

data class GifUIModel(
    var title: String = "",
    var original: ImageUIModel = ImageUIModel(),
    var small: ImageUIModel = ImageUIModel()
)

data class ImageUIModel(
    var url: String? = null,
    var webp: String? = null
)