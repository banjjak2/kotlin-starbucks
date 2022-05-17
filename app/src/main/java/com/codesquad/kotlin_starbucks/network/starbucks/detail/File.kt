package com.codesquad.kotlin_starbucks.network.starbucks.detail

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class File(

    @Json(name = "file_NAME")
    val fileName: String,

    @Json(name = "file_PATH")
    val filePath: String,

    @Json(name = "img_UPLOAD_PATH")
    val imageUploadPath: String
)