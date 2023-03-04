package com.uthus.alebeer.data.datasource.local

import com.uthus.alebeer.data.entity.BeerEntity
import com.uthus.alebeer.util.statemanagement.ResultState
import com.uthus.alebeer.util.statemanagement.StateHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface AleBeerLocalDataSource {
    fun getAllBeers(): Flow<ResultState<List<BeerEntity>>>
    fun insertBeer(beerEntity: BeerEntity): Flow<ResultState<Long?>>
    fun deleteBeer(id: Long): Flow<ResultState<Int?>>
    fun updateBeer(note: String, id: Long): Flow<ResultState<Int?>>
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

    override fun deleteBeer(id: Long): Flow<ResultState<Int?>> = flow {
        val result = StateHandler.execute {
            beerDao.deleteBeer(id)
        }
        emit(result)
    }

    override fun updateBeer(note: String, id: Long): Flow<ResultState<Int?>> = flow {
        val result = StateHandler.execute {
            beerDao.updateBeer(note = note, id = id)
        }
        emit(result)
    }


}

enum class InsertResult(val result: Long) {
    DEFAULT(-1)
}