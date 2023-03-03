package com.uthus.alebeer.data.datasource.local

import android.app.Application
import android.content.Context
import com.uthus.alebeer.data.entity.BeerEntity
import com.uthus.alebeer.util.localdb.BeerRoomDatabase
import com.uthus.alebeer.util.statemanagement.ResultState
import com.uthus.alebeer.util.statemanagement.StateHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface AleBeerLocalDataSource {
    fun getAllBeers(): Flow<ResultState<List<BeerEntity>>>
    fun insertBeer(beerEntity: BeerEntity): Flow<ResultState<Long?>>
}

class AleBeerLocalDataSourceImpl(private val beerDao: BeerDao) : AleBeerLocalDataSource {

    override fun getAllBeers(): Flow<ResultState<List<BeerEntity>>> = flow {
        val result = StateHandler.execute { beerDao.getAllBeers() }
        emit(result)
    }


    override fun insertBeer(beerEntity: BeerEntity): Flow<ResultState<Long?>> = flow {
        var result =
            StateHandler.execute {
                beerDao.insertBeer(beerEntity)
            }
        emit(result)
    }

}

enum class InsertResult(val result: Long) {
    DEFAULT(-1)
}