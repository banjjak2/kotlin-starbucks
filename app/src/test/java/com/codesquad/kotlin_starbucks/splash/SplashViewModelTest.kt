package com.codesquad.kotlin_starbucks.splash

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.codesquad.kotlin_starbucks.MainDispatcherRule
import com.codesquad.kotlin_starbucks.R
import com.codesquad.kotlin_starbucks.getOrAwaitValue
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SplashViewModelTest {
    private lateinit var splashViewModel: SplashViewModel

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {
        splashViewModel = SplashViewModel()
    }

    @Test
    fun getEventDetail_ConnectAndGetData_Success() = runTest {
        val expected = EventDetail(
            "스타벅스트",
            "2021년 10월 11일 ~ 10월 22일",
            "스타벅스트 리워드 회원",
            "기간 내 **오후 2시~6시** 등록된 카드로 주문시 **영수증당 별추가** 증정",
            "스타벅스트 딜리버리 음료"
        )

        splashViewModel.getEventDetail(SplashViewModel.EVENT_JSON_URL)

        val result = splashViewModel.eventDataDetail.getOrAwaitValue()
        Truth.assertThat(result).isEqualTo(expected)
    }

    @Test
    fun getEventDetail_Connect_Failure() = runTest {
        splashViewModel.getEventDetail(
            "https://localhost"
        )

        val result = splashViewModel.errorMessage.getOrAwaitValue()
        Truth.assertThat(result).isEqualTo(R.string.server_error_string)
    }

    @Test
    fun getEventDetail_ParseJson_Failure() = runTest {
        splashViewModel.getEventDetail(
            "https://api.codesquad.kr/starbuckst/"
        )

        val result = splashViewModel.errorMessage.getOrAwaitValue()
        Truth.assertThat(result).isEqualTo(R.string.json_parsing_error_string)
    }
}