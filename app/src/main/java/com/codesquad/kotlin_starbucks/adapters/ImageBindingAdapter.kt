package com.codesquad.kotlin_starbucks.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageFromUrl")
fun setImageFromUrl(imageView: ImageView, url: String?) {
    url?.let {
        Glide.with(imageView)
            .load(it)
            .into(imageView)
    }
}

@BindingAdapter("circleCropImageFromUrl")
fun setCircleCropImageFromUrl(imageView: ImageView, url: String?) {
    url?.let {
        Glide.with(imageView)
            .load(it)
            .circleCrop()
            .into(imageView)
    }
}