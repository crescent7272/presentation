package com.egeninsesi.news.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egeninsesi.news.data.model.CategoryList
import com.egeninsesi.news.data.model.NewsList
import com.egeninsesi.news.domain.usecase.MainActivityUseCase
import com.egeninsesi.news.ui.main.UiState
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
class MainFragmentViewModel @Inject constructor(
    private val useCase: MainActivityUseCase
) : ViewModel() {

    var _mutableNewsList = MutableLiveData<NewsList>()
    var mutableNewsList : LiveData<NewsList> = _mutableNewsList

    val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init{
        Timber.d("init")
        getNews()
    }

    fun onRefresh() {
        Timber.d("onRefresh")
        _isLoading.value = true
        getNews()
    }

    private fun getNews() {

        viewModelScope.launch {
            Timber.d("MainFragmentViewModel.launch")

            useCase.getNews()
                .catch { error ->
                    Timber.e(error.message)
                    _isLoading.value = false
                }
                .collect { data ->
                    _isLoading.value = false
                    _mutableNewsList.value = data
                    Timber.d("first = ")
                    Timber.d("data MainFragmentViewModel = "+ Gson().toJson(data))
                }
        }
    }
}