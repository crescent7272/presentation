package com.egeninsesi.news.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class NewsList(
    @SerializedName("news")
    @Expose
    var news: ArrayList<News> = ArrayList(),

    @SerializedName("headlines")
    @Expose
    var headlines: ArrayList<News> = ArrayList()
)
