package com.codesquad.kotlin_starbucks.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.codesquad.kotlin_starbucks.R

class FavoriteProductsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_favorite_products,
            container,
            false
        )
    }
}