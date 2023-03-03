package com.uthus.alebeer.util.statemanagement


object StateHandler {
     suspend fun <T : Any> execute(call: suspend () -> T) : ResultState<T> {
        return try{
            ResultState.Success(call())
        } catch (t: Throwable) {
            ResultState.Error(t)
        }
    }
}
