package com.uthus.alebeer.data.api

import retrofit2.http.GET
import retrofit2.http.Query

interface AleBeerApi {
    @GET("sample-data")
    suspend fun getBeers(@Query("page") page: Int, @Query("limit") limit: Int) : String
}