package com.codesquad.kotlin_starbucks.network.home

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface HomeService {
    companion object {
        private const val API_URL = "https://api.codesquad.kr/"

        fun create(skipNetworkInterceptor: Interceptor? = null): HomeService {
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
                .create(HomeService::class.java)
        }
    }

    @GET("starbuckst")
    suspend fun getHomeData(): HomeData
}