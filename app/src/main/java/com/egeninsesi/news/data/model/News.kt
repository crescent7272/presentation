package com.egeninsesi.news.data.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

data class News(

    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("title")
    @Expose
    var title: String? = null,

    @SerializedName("published_at")
    @Expose
    var publishedAt: String? = null,

    @SerializedName("category_id")
    @Expose
    var categoryId: Int? = null,

    @SerializedName("category_title")
    @Expose
    var categoryTitle: String? = null,

    @SerializedName("image_path")
    @Expose
    var imagePath: String? = null,

    @SerializedName("content")
    @Expose
    var content: String? = null,

    @SerializedName("summary")
    @Expose
    var summary: String? = null,

    @SerializedName("url")
    @Expose
    var url: String? = null
)