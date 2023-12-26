package com.gevcorst.mvidemo.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsData(
    @Json(name = "articles")
    val articles: List<Article>,
    @Json(name = "totalArticles")
    val totalArticles: Int
)
