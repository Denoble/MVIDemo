package com.gevcorst.mvidemo.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Article(
    @Json(name = "content")
    val content: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "image")
    val image: String,
    @Json(name = "publishedAt")
    val publishedAt: String,
    @Json(name = "source")
    val source: Source,
    @Json(name = "title")
    val title: String,
    @Json(name = "url")
    val url: String
)