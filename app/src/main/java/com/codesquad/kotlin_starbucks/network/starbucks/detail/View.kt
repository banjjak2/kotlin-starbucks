package com.codesquad.kotlin_starbucks.network.starbucks.detail

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class View(

    @Json(name = "allergy")
    val allergy: String,

    @Json(name = "caffeine")
    val caffeine: String,

    @Json(name = "cate_NAME")
    val cateName: String,

    @Json(name = "chabo")
    val chabo: String,

    @Json(name = "cholesterol")
    val cholesterol: String,

    @Json(name = "content")
    val content: String,

    @Json(name = "fat")
    val fat: String,

    @Json(name = "img_UPLOAD_PATH")
    val imageUploadPath: String,

    @Json(name = "kcal")
    val kcal: String,

    @Json(name = "product_CD")
    val productCd: String,

    @Json(name = "product_ENGNM")
    val productEngnm: String,

    @Json(name = "product_NM")
    val productNm: String,

    @Json(name = "protein")
    val protein: String,

    @Json(name = "recommend")
    val recommend: String,

    @Json(name = "sat_FAT")
    val satFat: String,

    @Json(name = "sodium")
    val sodium: String,

    @Json(name = "standard")
    val standard: String,

    @Json(name = "sugars")
    val sugars: String,

    @Json(name = "trans_FAT")
    val transFat: String,

    @Json(name = "unit")
    val unit: String
)