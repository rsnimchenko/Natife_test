package com.app.domain.model

import com.app.core.model.GifAPIModel
import com.app.core.model.ImageAPIModel

fun ImageAPIModel.toUI(): ImageUIModel = ImageUIModel(
    url = this.url,
    webp = this.webp
)

fun GifAPIModel.toUI(): GifUIModel = GifUIModel(
    title = this.title,
    original = this.images.original.toUI(),
    small = this.images.small.toUI()
)

fun List<GifAPIModel>.toUI(): List<GifUIModel> = this.map { it.toUI() }