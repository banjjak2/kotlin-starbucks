package com.codesquad.kotlin_starbucks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codesquad.kotlin_starbucks.splash.SplashFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dialog = SplashFragment()
        dialog.show(supportFragmentManager, "DIALOG")
    }
}