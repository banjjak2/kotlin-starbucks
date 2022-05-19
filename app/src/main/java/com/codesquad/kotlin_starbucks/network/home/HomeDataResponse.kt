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
    val nowRecommendIndexList: MenuIndexList,

    @Json(name = "your-recommand")
    val yourRecommendIndexList: MenuIndexList
)

@JsonClass(generateAdapter = true)
data class MainEvent(

    @Json(name = "img_UPLOAD_PATH")
    val imageUploadPath: String,

    @Json(name = "mob_THUM")
    val mobileThumbnail: String
)

@JsonClass(generateAdapter = true)
data class MenuIndexList(
    val products: List<String>
)
