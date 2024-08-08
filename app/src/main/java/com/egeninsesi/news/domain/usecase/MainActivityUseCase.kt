package com.egeninsesi.news.domain.usecase

import com.egeninsesi.news.data.model.Author
import com.egeninsesi.news.data.model.AuthorArticles
import com.egeninsesi.news.data.model.CategoryList
import com.egeninsesi.news.data.model.NewsList
import com.egeninsesi.news.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

class MainActivityUseCase @Inject constructor(
    private val repository: Repository
)  {

    fun getCategories() : Flow<CategoryList> {
        Timber.d("getCategories usecase")
        return repository.getCategories()
    }

    fun getNews(): Flow<NewsList> {
        return repository.getNews()
    }

    fun getCategoryNews(id:String): Flow<NewsList> {
        return repository.getCategoryNews(id)

    }

    fun getAuthorArticles(id:String): Flow<ArrayList<AuthorArticles>> {
        return repository.getAuthorArticles(id)

    }


    fun getAuthors(): Flow<ArrayList<Author>> {
        return repository.getAuthors()
    }


}