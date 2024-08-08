package com.egeninsesi.news.ui.main

import com.egeninsesi.news.data.model.Category
import com.egeninsesi.news.data.model.News

data class UiState (
    var categories: ArrayList<Category>? = null,
    var error: String = ""
)