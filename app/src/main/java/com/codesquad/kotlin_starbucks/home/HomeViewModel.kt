package com.codesquad.kotlin_starbucks.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codesquad.kotlin_starbucks.network.home.HomeData
import kotlinx.coroutines.launch

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    private var _homeData = MutableLiveData<HomeData>()
    val homeData: LiveData<HomeData> = _homeData

    fun getHomeData() {
        viewModelScope.launch {
            _homeData.value = homeRepository.getHomeData()
        }
    }
}