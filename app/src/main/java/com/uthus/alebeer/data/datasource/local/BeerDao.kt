package com.uthus.alebeer.data.datasource.local

import androidx.room.*
import com.uthus.alebeer.data.entity.BeerEntity
import com.uthus.alebeer.util.statemanagement.ResultState
import kotlinx.coroutines.flow.Flow

@Dao
interface BeerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBeer(beerEntity: BeerEntity) : Long

    @Query("SELECT * FROM beer_table")
    fun getAllBeers() : List<BeerEntity>

    @Query("DELETE FROM beer_table WHERE id=:id")
    fun deleteBeer(id: Long) : Int

    @Query("UPDATE beer_table SET note=:note WHERE id = :id")
    fun updateBeer(note:String, id: Long) : Int
}