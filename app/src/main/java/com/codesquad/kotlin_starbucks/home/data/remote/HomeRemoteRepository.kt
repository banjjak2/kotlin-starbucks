package com.codesquad.kotlin_starbucks.home.data.remote

import com.codesquad.kotlin_starbucks.data.MenuItem
import com.codesquad.kotlin_starbucks.home.data.HomeEventItem
import com.codesquad.kotlin_starbucks.network.home.HomeDataResponse
import com.codesquad.kotlin_starbucks.network.home.HomeService
import com.codesquad.kotlin_starbucks.network.starbucks.StarbucksService

class HomeRemoteRepository(
    private val homeNetwork: HomeService,
    private val starbucksNetwork: StarbucksService
) : HomeRepository {

    override suspend fun getHomeDataResponse(): HomeDataResponse {
        return homeNetwork.getHomeDataResponse()
    }

    override suspend fun getRecommendItems(
        rank: Int,
        visibleRank: Boolean,
        productId: String
    ): MenuItem? {
        val productDetail = starbucksNetwork.getProductTitle(productId)
        val productImage = starbucksNetwork.getProductImage(productId)

        if (productDetail.view == null) return null
        return MenuItem(
            productDetail.view.productNm,
            productImage.file[0].imageUploadPath + productImage.file[0].filePath,
            rank,
            visibleRank
        )
    }

    override suspend fun getHomeEvents(): List<HomeEventItem> {
        val events = starbucksNetwork.getEventsList("all").list
        val result = mutableListOf<HomeEventItem>()

        events.forEach {
            result.add(
                HomeEventItem(
                    it.title,
                    it.title,
                    it.imageUploadPath + "/upload/promotion/" + it.mobileThumbnail
                )
            )
        }

        return result
    }

    override suspend fun getWhatsNews(): List<HomeEventItem> {
        val whatsNews = starbucksNetwork.getWhatsNewList().list
        val result = mutableListOf<HomeEventItem>()

        whatsNews.forEach {
            result.add(
                HomeEventItem(
                    it.title,
                    it.newsDate,
                    "https://image.istarbucks.co.kr/upload/news/" + it.mobileThumbnail
                )
            )
        }

        return result
    }
}