package com.codesquad.kotlin_starbucks.home.data

data class HomeData(

    val displayName: String,

    val mainEventImagePath: String,

    val homeEvents: List<HomeItem.HomeEvent>,

    val yourRecommend: List<HomeItem.RecommendItem>,

    val nowRecommend: List<HomeItem.RecommendItem>
)
