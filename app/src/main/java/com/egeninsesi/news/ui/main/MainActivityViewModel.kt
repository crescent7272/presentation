package com.egeninsesi.news.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egeninsesi.news.data.model.CategoryList
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
class MainActivityViewModel @Inject constructor(
    private val useCase: MainActivityUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()
    private var categoryList: CategoryList? = null
    private var retrievedData: Boolean = false
    fun setRetrievedData(value: Boolean) {
        retrievedData = value
    }

    fun getRetrievedData(): Boolean {
        return retrievedData
    }
    init{

        Timber.d("init")
        getCategories()

    }

    private fun getCategories() {
        Timber.d("getCategories")

        viewModelScope.launch {
            Timber.d("viewModelScope.launch")

            useCase.getCategories()
                .catch { error ->
                    Timber.e(error.message)
                    _state.update { state ->
                        state.copy(error = ""+error.message, categories = categoryList?.categories)
                    }
                }
                .collect { data ->

                    Timber.d("first = ")
                    Timber.d("data = "+ Gson().toJson(data))
                    categoryList = data
                    _state.update { state ->
                        state.copy(error = "", categories = data.categories)
                    }
                }

        }
    }
}