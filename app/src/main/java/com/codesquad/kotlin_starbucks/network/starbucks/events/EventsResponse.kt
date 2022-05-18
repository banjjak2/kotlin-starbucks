package com.codesquad.kotlin_starbucks.network.starbucks.events

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EventsResponse(
    val list: List<Event>
)