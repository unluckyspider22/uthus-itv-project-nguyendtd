package com.uthus.alebeer.util.network.statemanagement


sealed class ResultState<out T> {
    object Loading : ResultState<Nothing?>()
    data class Success<T>(val data: T) : ResultState<T>()
    data class Error(val errorMessage: String?, val exception: Throwable?) : ResultState<Nothing?>()
}
