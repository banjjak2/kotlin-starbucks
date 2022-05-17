package com.codesquad.kotlin_starbucks.network.home

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class YourRecommend(
    val products: List<String>
)