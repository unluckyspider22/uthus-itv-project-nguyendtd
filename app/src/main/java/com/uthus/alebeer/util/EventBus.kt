package com.uthus.alebeer.util

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object EventBus {
    private val _events = MutableSharedFlow<Action>()
    val events = _events.asSharedFlow()

    suspend fun publishEvent(action: Action) = _events.emit(action)
}

enum class Action {
    DELETE,UPDATE,SAVE
}