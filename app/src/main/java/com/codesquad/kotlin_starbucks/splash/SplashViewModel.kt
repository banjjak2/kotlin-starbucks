package com.codesquad.kotlin_starbucks.splash

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codesquad.kotlin_starbucks.R
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonEncodingException
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class SplashViewModel : ViewModel() {
    companion object {
        const val EVENT_JSON_URL =
            "https://public.codesquad.kr/jk/boostcamp/starbuckst-loading.json"
    }

    private var _eventDataDetail = MutableLiveData<EventDetail>()
    val eventDataDetail: LiveData<EventDetail> = _eventDataDetail

    private var _errorMessage = MutableLiveData<Int>()
    val errorMessage: LiveData<Int> = _errorMessage

    private suspend fun getBodyFromUrl(eventJsonUrl: String): String {
        return withContext(Dispatchers.IO) {
            val result = StringBuilder()
            val url = URL(eventJsonUrl)
            (url.openConnection() as? HttpsURLConnection)?.run {
                requestMethod = "GET"
                connectTimeout = 5000
                readTimeout = 5000

                val bufferedReader = BufferedReader(
                    InputStreamReader(inputStream)
                )
                bufferedReader.forEachLine {
                    result.append(it).append('\n')
                }
            }
            result.trim().toString()
        }
    }

    fun getEventDetail(eventJsonUrl: String) {
        val moshi: Moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val jsonAdapter: JsonAdapter<EventDetail> = moshi.adapter(EventDetail::class.java)

        viewModelScope.launch {
            try {
                val result = getBodyFromUrl(eventJsonUrl)
                val eventDetail = withContext(Dispatchers.Default) {
                    jsonAdapter.fromJson(result)
                }
                eventDetail?.let {
                    _eventDataDetail.value = it
                }
            } catch (e: Exception) {
                e.printStackTrace()
                setErrorMessage(when (e) {
                    // JSON 형식과 데이터 클래스 형식이 다를 경우
                    is JsonDataException,
                    // 서버의 응답이 JSON 형식이 아닐경우
                    is JsonEncodingException -> R.string.json_parsing_error_string
                    else -> R.string.server_error_string
                })
            }
        }
    }

    private fun setErrorMessage(@StringRes errMsgRes: Int) {
        _errorMessage.value = errMsgRes
    }
}