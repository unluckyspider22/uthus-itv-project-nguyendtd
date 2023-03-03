package com.uthus.alebeer.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.uthus.alebeer.data.entity.BeerEntity
import com.uthus.alebeer.util.statemanagement.ResultState
import kotlinx.coroutines.flow.Flow

@Dao
interface BeerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBeer(beerEntity: BeerEntity) : Long

    @Query("SELECT * FROM beer_table")
    fun getAllBeers() : List<BeerEntity>
//    @Query("SELECT * FROM beer_table WHERE id = $id")
//    fun getSpecificBeer(id: String) : BeerEntity
}