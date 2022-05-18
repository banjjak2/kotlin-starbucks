package com.codesquad.kotlin_starbucks.network.home

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HomeDataResponse(

    @Json(name = "display-name")
    val displayName: String,

    @Json(name = "main-event")
    val mainEvent: MainEvent,

    @Json(name = "now-recommand")
    val nowRecommend: NowRecommend,

    @Json(name = "your-recommand")
    val yourRecommend: YourRecommend
)