package com.codesquad.kotlin_starbucks.home.data

sealed class HomeItem {
    data class RecommendItem(
        val idx: Int = -1,
        val title: String,
        val imagePath: String,
        val visibleRank: Boolean = false
    ): HomeItem()

    data class HomeEvent(
        val title: String,
        val imagePath: String
    ): HomeItem()
}
