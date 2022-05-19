package com.codesquad.kotlin_starbucks.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codesquad.kotlin_starbucks.home.data.HomeItem
import com.codesquad.kotlin_starbucks.home.data.remote.HomeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    private var _displayName = MutableStateFlow("")
    val displayName: StateFlow<String> = _displayName

    private var _mainEventImagePath = MutableStateFlow("")
    val mainEventImagePath: StateFlow<String> = _mainEventImagePath

    private var _homeEvents = MutableStateFlow<List<HomeItem.HomeEvent>>(listOf())
    val homeEvents: StateFlow<List<HomeItem.HomeEvent>> = _homeEvents

    private var _yourRecommendItems = MutableStateFlow<List<HomeItem.RecommendItem>>(listOf())
    val yourRecommendItems: StateFlow<List<HomeItem.RecommendItem>> = _yourRecommendItems

    private var _nowRecommendItems = MutableStateFlow<List<HomeItem.RecommendItem>>(listOf())
    val nowRecommendItems: StateFlow<List<HomeItem.RecommendItem>> = _nowRecommendItems

    fun getHomeData() {
        viewModelScope.launch {
            with(homeRepository.getHomeDataResponse()) {
                _displayName.value = displayName
                _mainEventImagePath.value =
                    mainEvent.imageUploadPath + mainEvent.mobileThumbnail

                launch {
                    _yourRecommendItems.value = getRecommendItems(false, yourRecommend.products)
                }

                launch {
                    _nowRecommendItems.value = getRecommendItems(true, nowRecommend.products)
                }

                launch {
                    _homeEvents.value = homeRepository.getHomeEvents()
                }
            }
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

    fun getWhatsNewList() {
        viewModelScope.launch {
            val result = homeRepository.getWhatsNews()
            Log.d("AAAA", result.toString())
        }
    }
}