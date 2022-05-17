package com.codesquad.kotlin_starbucks.home

import com.codesquad.kotlin_starbucks.network.home.HomeData
import com.codesquad.kotlin_starbucks.network.home.HomeService

class HomeRepository(
    private val network: HomeService
) {

    suspend fun getHomeData(): HomeData {
        return network.getHomeData()
    }
}