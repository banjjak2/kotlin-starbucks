package com.codesquad.kotlin_starbucks.adapters

import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import com.codesquad.kotlin_starbucks.R

@BindingAdapter("displayName")
fun setDisplayName(view: TextView, name: String?) {
    name?.let {
        val displayText = view.context.getString(R.string.display_name, name)
        val convertText = HtmlCompat.fromHtml(
            displayText,
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )

        view.text = convertText
    }
}