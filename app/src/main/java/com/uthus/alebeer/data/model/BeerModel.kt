package com.uthus.alebeer.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BeerModel(
    val price: String,
    val name: String,
    val rating: Rating,
    val image: String,
    val id: Long,
    val saleOffTime: Long
) : Parcelable

@Parcelize
data class Rating(
    val average: Double,
    val reviews: Int
): Parcelable
