package com.egeninsesi.news.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CategoryList(

    @SerializedName("categories")
    @Expose
    var categories: ArrayList<Category> = ArrayList()
)
