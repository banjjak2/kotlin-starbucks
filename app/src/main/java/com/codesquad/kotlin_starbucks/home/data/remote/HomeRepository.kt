package com.codesquad.kotlin_starbucks.home.data.remote

import com.codesquad.kotlin_starbucks.home.data.HomeItem
import com.codesquad.kotlin_starbucks.home.data.RemoteRepository
import com.codesquad.kotlin_starbucks.home.data.WhatsNew
import com.codesquad.kotlin_starbucks.network.home.HomeDataResponse
import com.codesquad.kotlin_starbucks.network.home.HomeService
import com.codesquad.kotlin_starbucks.network.starbucks.StarbucksService

class HomeRepository(
    private val homeNetwork: HomeService,
    private val starbucksNetwork: StarbucksService
) : RemoteRepository {

    override suspend fun getHomeDataResponse(): HomeDataResponse {
        return homeNetwork.getHomeDataResponse()
    }

    override suspend fun getRecommendItems(
        idx: Int,
        visibleRank: Boolean,
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

    override suspend fun getHomeEvents(): List<HomeItem.HomeEvent> {
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

    override suspend fun getWhatsNews(): List<WhatsNew> {
        val whatsNews = starbucksNetwork.getWhatsNewList().list
        val result = mutableListOf<WhatsNew>()

        whatsNews.forEach {
            result.add(
                WhatsNew(
                    it.title,
                    it.newsDate,
                    "https://image.istarbucks.co.kr/upload/news/" + it.mobileThumbnail
                )
            )
        }

        return result
    }
}