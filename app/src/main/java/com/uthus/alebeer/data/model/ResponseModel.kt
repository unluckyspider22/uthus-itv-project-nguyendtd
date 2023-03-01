package com.uthus.alebeer.data.model

import com.squareup.moshi.Json

data class ResponseModel<T>(
    @Json(name = "status")
    val status: String,
    @Json(name = "total")
    val total: Int,
    @Json(name = "message")
    val message: String,
    @Json(name = "loadMore")
    val loadMore: Boolean,
    @Json(name = "data")
    val data: T)