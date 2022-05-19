package com.codesquad.kotlin_starbucks.home.data.remote

import com.codesquad.kotlin_starbucks.data.MenuItem
import com.codesquad.kotlin_starbucks.home.data.HomeEventItem
import com.codesquad.kotlin_starbucks.network.home.HomeDataResponse

interface HomeRepository {

    suspend fun getHomeDataResponse(): HomeDataResponse

    suspend fun getRecommendItems(
        rank: Int,
        visibleRank: Boolean,
        productId: String
    ): MenuItem?

    suspend fun getHomeEvents(): List<HomeEventItem>

    suspend fun getWhatsNews(): List<HomeEventItem>
}