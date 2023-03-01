package com.uthus.alebeer.util.network
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL =
    "https://apps.uthus.vn/api/api-testing/"
object AleBeerNetwork{
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
     val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

}