package com.codesquad.kotlin_starbucks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.codesquad.kotlin_starbucks.splash.SplashViewModel
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>) = with(modelClass) {
        when {
            isAssignableFrom(SplashViewModel::class.java) -> {
                SplashViewModel()
            }
            else -> throw IllegalArgumentException("Unknown ViewModel $modelClass")
        } as T
    }
}