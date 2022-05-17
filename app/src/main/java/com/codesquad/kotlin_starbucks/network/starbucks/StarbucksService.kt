package com.codesquad.kotlin_starbucks.network.starbucks

import com.codesquad.kotlin_starbucks.network.starbucks.detail.ProductDetail
import com.codesquad.kotlin_starbucks.network.starbucks.detail.ProductDetailImage
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface StarbucksService {
    companion object {
        private const val API_URL = "https://www.starbucks.co.kr/"

        fun create(skipNetworkInterceptor: Interceptor? = null): StarbucksService {
            val interceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            val client = with(OkHttpClient.Builder()) {
                skipNetworkInterceptor?.let {
                    addInterceptor(it)
                }
                addInterceptor(interceptor)
                build()
            }

            return Retrofit.Builder()
                .baseUrl(API_URL)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(StarbucksService::class.java)
        }
    }

    @POST("menu/productViewAjax.do")
    suspend fun getProductTitle(@Query("product_cd") productCd: String): ProductDetail

    @POST("menu/productFileAjax.do")
    suspend fun getProductImage(@Query("PRODUCT_CD") productCd: String): ProductDetailImage
}