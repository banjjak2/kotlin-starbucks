package com.codesquad.kotlin_starbucks.home.data

import com.codesquad.kotlin_starbucks.network.home.HomeDataResponse

interface RemoteRepository {

    suspend fun getHomeDataResponse(): HomeDataResponse

    suspend fun getRecommendItems(
        idx: Int = -1,
        visibleRank: Boolean = false,
        productId: String
    ): HomeItem.RecommendItem?

    suspend fun getHomeEvents(): List<HomeItem.HomeEvent>
}