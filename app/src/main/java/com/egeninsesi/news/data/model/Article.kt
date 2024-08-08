package com.egeninsesi.news.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Article(
    @SerializedName("id")
    @Expose
    val id: Int? = null,

    @SerializedName("title")
    @Expose
    val title: String? = null,

    @SerializedName("published_at")
    @Expose
    val publishedAt: String? = null
)