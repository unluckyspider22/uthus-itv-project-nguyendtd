package com.uthus.alebeer.data.datasource.local

import android.app.Application
import com.uthus.alebeer.data.entity.BeerEntity
import com.uthus.alebeer.util.localdb.BeerRoomDatabase

interface AleBeerLocalDataSource {
    suspend fun getAllBeers(): List<BeerEntity>
    suspend fun insertBeer(beerEntity: BeerEntity)
}

class AleBeerLocalDataSourceImpl : AleBeerLocalDataSource {
    private val appContext = Application().applicationContext
    private val beerDatabase: BeerRoomDatabase? =
        BeerRoomDatabase.getDatabase(appContext)
    private val beerDao = beerDatabase?.beerDao()

    override suspend fun getAllBeers(): List<BeerEntity> {
        return beerDao?.getAllBeers() ?: listOf()
    }

    override suspend fun insertBeer(beerEntity: BeerEntity) {
        BeerRoomDatabase.databaseWriteExecutor.execute { beerDao?.insertBeer(beerEntity) }
    }

}