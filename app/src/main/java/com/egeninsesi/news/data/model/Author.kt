package com.egeninsesi.news.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Author(

    @SerializedName("id")
    @Expose
    val id: Int? = null,

    @SerializedName("first_name")
    @Expose
    val firstName: String? = null,

    @SerializedName("last_name")
    @Expose
    val lastName: String? = null,

    @SerializedName("email")
    @Expose
    val email: String? = null,

    @SerializedName("image_path")
    @Expose
    val imagePath: String? = null,

    @SerializedName("last_article_title")
    @Expose
    val lastArticleTitle: String? = null,

    @SerializedName("last_article_content")
    @Expose
    val lastArticleContent: String? = null,

    @SerializedName("url")
    @Expose
    val url: String? = null,

    @SerializedName("published_at")
    @Expose
    val publishedAt: String? = null
)