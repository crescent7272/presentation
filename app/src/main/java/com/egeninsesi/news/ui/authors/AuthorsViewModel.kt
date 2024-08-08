package com.egeninsesi.news.ui.authors

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.egeninsesi.news.data.model.Author
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
class AuthorsViewModel @Inject constructor(
    private val useCase: MainActivityUseCase
) : ViewModel() {

    var _mutableAuthorList = MutableLiveData<List<Author>>()
    var mutableAuthorList : LiveData<List<Author>> = _mutableAuthorList

    val _mutableError = MutableLiveData<String>()
    val mutableError : LiveData<String> = _mutableError

    val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    init {
        getAuthors()
    }
    fun onRefresh() {
        Timber.d("onRefresh")
        _isLoading.value = true
        getAuthors()
    }
    fun getAuthors() {

        viewModelScope.launch {
            Timber.d("AuthorsViewModel.launch")

            useCase.getAuthors()
                .catch { error ->
                    Timber.e(error.message)
                    _isLoading.value = false
                    _mutableError.value = error.message
                }
                .collect { data ->
                    _isLoading.value = false
                    _mutableAuthorList.value = data
                    Timber.d("first = ")
                    Timber.d("data AuthorsViewModel = "+ Gson().toJson(data))
                }
        }
    }
}