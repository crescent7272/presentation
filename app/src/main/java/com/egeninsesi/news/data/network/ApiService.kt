package com.egeninsesi.news.data.network

import com.egeninsesi.news.data.model.Author
import com.egeninsesi.news.data.model.AuthorArticles
import com.egeninsesi.news.data.model.CategoryList
import com.egeninsesi.news.data.model.NewsList
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("categories")
    suspend fun getCategories(): CategoryList

    @GET("news")
    suspend fun getNews(): NewsList


    @GET("authors")
    suspend fun getAuthors(): ArrayList<Author>

    @GET("authors/{id}")
    suspend fun getAuthorArticles(@Path("id") id: String): ArrayList<AuthorArticles>

    @GET("category/{id}")
    suspend fun getCategoryNews(@Path("id") id: String): NewsList
}