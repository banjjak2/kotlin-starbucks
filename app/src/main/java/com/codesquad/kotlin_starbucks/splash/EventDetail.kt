package com.codesquad.kotlin_starbucks.splash

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EventDetail(
    val title: String,
    val range: String,
    val target: String,
    val description: String,
    @Json(name = "event-products")
    val eventProducts: String
)
