package com.uthus.alebeer.data.api

import com.uthus.alebeer.data.model.BeerModel
import com.uthus.alebeer.data.model.ResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface AleBeerApi {
    @GET("sample-data")
    suspend fun getBeers(@Query("page") page: Int, @Query("limit") limit: Int) : ResponseModel<List<BeerModel>>
}