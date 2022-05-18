package com.codesquad.kotlin_starbucks.home

import com.codesquad.kotlin_starbucks.home.data.HomeItem
import com.codesquad.kotlin_starbucks.network.home.HomeDataResponse
import com.codesquad.kotlin_starbucks.network.home.HomeService
import com.codesquad.kotlin_starbucks.network.starbucks.StarbucksService

class HomeRepository(
    private val homeNetwork: HomeService,
    private val starbucksNetwork: StarbucksService
) {

    suspend fun getHomeDataResponse(): HomeDataResponse {
        return homeNetwork.getHomeDataResponse()
    }

    suspend fun getRecommendItems(
        idx: Int = -1,
        visibleRank: Boolean = false,
        productId: String
    ): HomeItem.RecommendItem? {
        val productDetail = starbucksNetwork.getProductTitle(productId)
        val productImage = starbucksNetwork.getProductImage(productId)

        if (productDetail.view == null) return null
        return HomeItem.RecommendItem(
            idx,
            productDetail.view.productNm,
            productImage.file[0].imageUploadPath + productImage.file[0].filePath,
            visibleRank
        )
    }

    suspend fun getHomeEvents(): List<HomeItem.HomeEvent> {
        val events = starbucksNetwork.getEventsList("all").list
        val result = mutableListOf<HomeItem.HomeEvent>()

        events.forEach {
            result.add(
                HomeItem.HomeEvent(
                    it.title,
                    it.imageUploadPath + "/upload/promotion/" + it.mobileThumbnail
                )
            )
        }

        return result
    }
}