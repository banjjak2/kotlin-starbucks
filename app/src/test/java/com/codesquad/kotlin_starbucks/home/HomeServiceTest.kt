package com.codesquad.kotlin_starbucks.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.codesquad.kotlin_starbucks.MainDispatcherRule
import com.codesquad.kotlin_starbucks.network.home.HomeData
import com.codesquad.kotlin_starbucks.network.home.HomeService
import com.google.common.truth.Truth
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeServiceTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun getHomeData_success() = runTest {
        val expectedJson = """
            {
                "display-name" : "사과CRONG",
                "your-recommand" : {
                    "products" : ["9200000002502", "110569", "9200000002403", "9300000000036"]
                },
                "main-event" : {
                    "img_UPLOAD_PATH": "https://image.istarbucks.co.kr/upload/promotion/",
                    "mob_THUM": "APP_THUM_20210719090612417.jpg"
                },
                "now-recommand" : {
                    "products" : ["9200000002403", "106509", "9200000000479", "9200000002502", "9200000000433"]
                }
            }
        """.trimIndent()
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val jsonAdapter: JsonAdapter<HomeData> = moshi.adapter(HomeData::class.java)
        val expectedData = jsonAdapter.fromJson(expectedJson)

        val network = HomeService.create(SkipNetworkInterceptor())
        val result = network.getHomeData()
        Truth.assertThat(result).isEqualTo(expectedData)
    }
}