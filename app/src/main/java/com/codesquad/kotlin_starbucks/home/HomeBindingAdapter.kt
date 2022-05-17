package com.codesquad.kotlin_starbucks.home

import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.codesquad.kotlin_starbucks.R
import com.codesquad.kotlin_starbucks.network.home.MainEvent

@BindingAdapter("display_name")
fun setDisplayName(view: TextView, name: String?) {
    name?.let {
        val displayText = view.context.getString(R.string.display_name, name)
        val convertText = HtmlCompat.fromHtml(
            displayText,
            FROM_HTML_MODE_LEGACY
        )

        view.text = convertText
    }
}

@BindingAdapter("set_image_from_url")
fun setMainEventImageFromUrl(imageView: ImageView, mainEvent: MainEvent?) {
    mainEvent?.let {
        val url = it.imageUploadPath + it.mobileThumbnail
        Glide.with(imageView.context)
            .load(url)
            .into(imageView)
    }
}