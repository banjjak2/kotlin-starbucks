package com.codesquad.kotlin_starbucks.network.starbucks.detail

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductDetailImage(
    val `file`: List<File>
)