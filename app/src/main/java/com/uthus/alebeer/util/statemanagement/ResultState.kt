package com.uthus.alebeer.util.statemanagement


sealed class ResultState<out T> {
    object Loading : ResultState<Nothing>()
    data class Success<T>(val data: T) : ResultState<T>()
    data class Error(val exception: Throwable?) : ResultState<Nothing>()
}

inline fun <T, R> ResultState<T>.map(crossinline transform: (input: T) -> R): ResultState<R> =
    when (this) {
        is ResultState.Success -> ResultState.Success(transform(this.data))
        is ResultState.Error -> ResultState.Error(this.exception)
        is ResultState.Loading -> this
    }
