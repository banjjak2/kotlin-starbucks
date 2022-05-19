package com.codesquad.kotlin_starbucks.data

data class MenuItem(
    val title: String,
    val imageUrl: String,
    val rank: Int,
    val visibleRank: Boolean = false
)
