package com.codesquad.kotlin_starbucks.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codesquad.kotlin_starbucks.data.MenuItem
import com.codesquad.kotlin_starbucks.home.data.HomeEventItem
import com.codesquad.kotlin_starbucks.home.data.remote.HomeRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    private var _displayName = MutableStateFlow("")
    val displayName: StateFlow<String> = _displayName

    private var _mainEventImagePath = MutableStateFlow("")
    val mainEventImagePath = _mainEventImagePath.asStateFlow()

    private var _homeEvents = MutableStateFlow<List<HomeEventItem>>(listOf())
    val homeEvents = _homeEvents.asStateFlow()

    private var _yourRecommendItems = MutableStateFlow<List<MenuItem>>(listOf())
    val yourRecommendItems = _yourRecommendItems.asStateFlow()

    private var _nowRecommendItems = MutableStateFlow<List<MenuItem>>(listOf())
    val nowRecommendItems = _nowRecommendItems.asStateFlow()

    fun getHomeData() {
        viewModelScope.launch {
            val homeDataResponse = homeRepository.getHomeDataResponse()
            with(homeDataResponse) {
                _displayName.emit(displayName)
                _mainEventImagePath.emit(
                    mainEvent.imageUploadPath + mainEvent.mobileThumbnail
                )

                launch {
                    _yourRecommendItems.emit(
                        getMenuItems(false, yourRecommendIndexList.products)
                    )
                }

                launch {
                    _nowRecommendItems.emit(getMenuItems(true, nowRecommendIndexList.products))
                }

                launch {
                    _homeEvents.emit(homeRepository.getHomeEvents())
                }
            }
        }
    }

    private suspend fun getMenuItems(
        visibleRank: Boolean = false,
        productsId: List<String>
    ): List<MenuItem> {
        val result = mutableListOf<MenuItem>()
        var index = 1
        productsId.forEach { productId ->
            val recommend =
                homeRepository.getRecommendItems(index++, visibleRank, productId)
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