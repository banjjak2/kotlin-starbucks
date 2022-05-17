package com.codesquad.kotlin_starbucks.network.home

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MainEvent(

    @Json(name = "img_UPLOAD_PATH")
    val imageUploadPath: String,

    @Json(name = "mob_THUM")
    val mobileThumbnail: String
)