package com.egeninsesi.news.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egeninsesi.news.data.model.Category
import com.egeninsesi.news.data.model.NewsList
import com.egeninsesi.news.domain.usecase.MainActivityUseCase
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CategoryFragmentViewModel @Inject constructor(
    private val useCase: MainActivityUseCase
) : ViewModel() {

    private var newsList: NewsList? = null

    var _mutableNewsList = MutableLiveData<NewsList>()
    val mutableNewsList: LiveData<NewsList> get() = _mutableNewsList

    val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    lateinit var categoryId : String
    var categorySlug = ""

    init{
        Timber.d("init")
        Timber.d("categorySlug $categorySlug")
    }

    fun onRefresh() {
        Timber.d("onRefresh")
        _isLoading.value = true
        getCategoryNews(categoryId)
    }

    fun getCategoryNews(id:String) {

        categoryId = id
        viewModelScope.launch {
            Timber.d("DetailsFragmentViewModel.launch")

            useCase.getCategoryNews(id)
                .catch { error ->
                    Timber.e(error.message)
                    _isLoading.value = false
                }
                .collect { data ->

                    Timber.d("first = ")
                    Timber.d("data DetailsFragmentViewModel = "+ Gson().toJson(data))
                    _isLoading.value = false
                    _mutableNewsList.value = data
                }

        }
    }

}