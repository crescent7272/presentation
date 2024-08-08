package com.egeninsesi.news.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AuthorArticles(
    @SerializedName("id")
    @Expose
    val id: Int? = null,

    @SerializedName("full_name")
    @Expose
    val fullName: String? = null,

    @SerializedName("image_path")
    @Expose
    val imagePath: String? = null,

    @SerializedName("articles")
    @Expose
    val articles: ArrayList<Article>? = null
)