package com.uthus.alebeer.util.network
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.Retrofit
import retrofit2.create

private const val BASE_URL =
    "https://apps.uthus.vn/api/api-testing/"
object AleBeerNetwork{

     val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

}