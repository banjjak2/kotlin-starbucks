package com.codesquad.kotlin_starbucks.network.starbucks.whatsnew

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WhatsNewResponse(
    val list: List<NewsItem>
)

@JsonClass(generateAdapter = true)
data class NewsItem (

    @Json(name = "app_thnl_img_name")
    val mobileThumbnail: String,

    @Json(name = "news_dt")
    val newsDate: String,

    @Json(name = "title")
    val title: String
)
