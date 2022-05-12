package com.codesquad.kotlin_starbucks.splash

import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY
import androidx.databinding.BindingAdapter

@BindingAdapter("markdown_text")
fun setMarkdownText(textView: TextView, text: String?) {
    text?.let {
        val boldRegex = "(\\*\\*|__)(.*?)\\1"
        val regex = Regex(boldRegex)
        val findBoldTexts = regex.findAll(text)
        var result = it
        findBoldTexts.forEach { text ->
            val innerText = text.value.substring(2, text.value.length - 2)
            result = result.replace(text.value, "<b>${innerText}</b>")
        }

        textView.text = HtmlCompat.fromHtml(
            result,
            FROM_HTML_MODE_LEGACY
        )
    }
}