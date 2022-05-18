package com.codesquad.kotlin_starbucks.network.starbucks.events

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Event (

    @Json(name = "img_UPLOAD_PATH")
    val imageUploadPath: String,

    @Json(name = "mob_THUM")
    val mobileThumbnail: String,

    @Json(name = "title")
    val title: String
)
