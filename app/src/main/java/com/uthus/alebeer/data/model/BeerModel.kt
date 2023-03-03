package com.uthus.alebeer.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class BeerModel(
    @Json(name = "price")
    val price: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "rating")
    val rating: Rating?,
    @Json(name = "image")
    val image: String,
    @Json(name = "id")
    val id: Long,
    @Json(name = "sale_off_time")
    val saleOffTime: Long,
    var note: String? = null
) : Parcelable {
}

@Parcelize
data class Rating(
    @Json(name = "average")
    val average: Double,
    @Json(name = "reviews")
    val reviews: Int
): Parcelable
