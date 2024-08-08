package com.egeninsesi.news.data.response

sealed class ResponseDefaultResource<T>(){
    class Success<T>(val data: T): ResponseDefaultResource<T>()

    class Error<nothing>(val error: String): ResponseDefaultResource<nothing>()
}
