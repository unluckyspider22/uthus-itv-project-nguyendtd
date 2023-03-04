package com.uthus.alebeer.data.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "beer_table")
data class BeerEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "price")
    val price: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "image")
    val image: String,
    @ColumnInfo(name = "sale_off_time")
    val saleOffTime: Long,
    @ColumnInfo(name = "note")
    val note: String,
    @ColumnInfo(name = "isSaved")
    val isSaved: Boolean
)
@Entity(tableName = "rating_table")
data class RatingEntity(
    @ColumnInfo(name = "average")
    val average: Double,
    @ColumnInfo(name = "reviews")
    val reviews: Int
)