package com.egeninsesi.news.data.repository

import com.egeninsesi.news.data.model.Author
import com.egeninsesi.news.data.model.AuthorArticles
import com.egeninsesi.news.data.model.CategoryList
import com.google.gson.Gson
import com.egeninsesi.news.data.model.NewsList
import com.egeninsesi.news.data.network.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: ApiService) {

    fun getAuthors() : Flow<ArrayList<Author>> = flow {
        Timber.d("getAuthors repo")

        val data = apiService.getAuthors()
        val gson = Gson()
        Timber.d("data = "+gson.toJson(data))
        emit(data)
    }
    fun getAuthorArticles(id:String) : Flow<ArrayList<AuthorArticles>> = flow {
        Timber.d("getAuthorARticles repo")

        val data = apiService.getAuthorArticles(id)
        val gson = Gson()
        Timber.d("data = "+gson.toJson(data))
        emit(data)
    }

    fun getCategories() : Flow<CategoryList> = flow {
        Timber.d("getCategories repo")

        val data = apiService.getCategories()
        val gson = Gson()
        Timber.d("data = "+gson.toJson(data))
        emit(data)
    }
    fun getNews() : Flow<NewsList> = flow {

        val data = apiService.getNews()
        val gson = Gson()
        Timber.d("data = "+gson.toJson(data))
        emit(data)
    }

    fun getCategoryNews(id:String) : Flow<NewsList> = flow {
        val data = apiService.getCategoryNews(id)
        val gson = Gson()
        Timber.d("data = "+gson.toJson(data))
        emit(data)
    }

}