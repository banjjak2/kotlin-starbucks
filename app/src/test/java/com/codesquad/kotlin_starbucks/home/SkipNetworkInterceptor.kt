package com.codesquad.kotlin_starbucks.home

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody

class SkipNetworkInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        Thread.sleep(500)

        return makeCorrectResult(chain.request())
    }

    private fun makeCorrectResult(request: Request): Response {
        val json = """
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

        return Response.Builder()
            .code(200)
            .request(request)
            .protocol(Protocol.HTTP_1_1)
            .message("OK")
            .body(json.toResponseBody("application/json; charset=utf-8".toMediaTypeOrNull()))
            .build()
    }
}