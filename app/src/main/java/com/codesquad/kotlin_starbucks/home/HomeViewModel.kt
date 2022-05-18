package com.codesquad.kotlin_starbucks.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codesquad.kotlin_starbucks.home.data.HomeData
import com.codesquad.kotlin_starbucks.home.data.HomeItem
import kotlinx.coroutines.launch

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    private var _homeData = MutableLiveData<HomeData>()
    val homeData: LiveData<HomeData> = _homeData

    fun getHomeData() {
        viewModelScope.launch {
            val homeDataResponse = homeRepository.getHomeDataResponse()
            val result = with(homeDataResponse) {
                HomeData(
                    displayName,
                    mainEvent.imageUploadPath + mainEvent.mobileThumbnail,
                    homeRepository.getHomeEvents(),
                    getRecommendItems(false, yourRecommend.products),
                    getRecommendItems(true, nowRecommend.products)
                )
            }

            _homeData.value = result
        }
    }

    private suspend fun getRecommendItems(
        visibleRank: Boolean = false,
        productsId: List<String>
    ): List<HomeItem.RecommendItem> {
        val result = mutableListOf<HomeItem.RecommendItem>()
        var index = 1
        productsId.forEach { productId ->
            val recommend = homeRepository.getRecommendItems(index++, visibleRank, productId)
            recommend?.let {
                result.add(recommend)
            } ?: index--
        }

        return result
    }
}