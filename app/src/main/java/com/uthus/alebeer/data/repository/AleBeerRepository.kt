package com.uthus.alebeer.data.repository

import com.uthus.alebeer.data.datasource.local.AleBeerLocalDataSource
import com.uthus.alebeer.data.datasource.local.AleBeerLocalDataSourceImpl
import com.uthus.alebeer.data.datasource.local.InsertResult
import com.uthus.alebeer.data.datasource.remote.AleBeerRemoteDataSource
import com.uthus.alebeer.data.datasource.remote.AleBeerRemoteDataSourceImpl
import com.uthus.alebeer.data.entity.BeerEntity
import com.uthus.alebeer.data.model.BeerModel
import com.uthus.alebeer.data.model.ResponseModel
import com.uthus.alebeer.util.statemanagement.ResultState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn

interface AleBeerRepository {
    fun getBeers(): Flow<ResultState<ResponseModel<List<BeerModel>>>>
    fun getLocalBeers(): Flow<ResultState<List<BeerEntity>>>
    fun saveFavoriteToLocal(beerEntity: BeerEntity): Flow<ResultState<Long?>>
}

class AleBeerRepositoryImpl(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val remoteDataSource: AleBeerRemoteDataSource = AleBeerRemoteDataSourceImpl(),
    private val localDataSource: AleBeerLocalDataSource
) :
    AleBeerRepository {

    override fun getBeers(): Flow<ResultState<ResponseModel<List<BeerModel>>>> =
        remoteDataSource.getBeers().flowOn(dispatcher)

    override fun getLocalBeers(): Flow<ResultState<List<BeerEntity>>> =
        localDataSource.getAllBeers().flowOn(dispatcher)


    override fun saveFavoriteToLocal(beerEntity: BeerEntity): Flow<ResultState<Long?>> =
        localDataSource.insertBeer(beerEntity).flowOn(dispatcher)
//        flow {
//            val result = localDataSource.insertBeer(beerEntity) ?: InsertResult.DEFAULT.result
//            emit(ResultState.Success(result))
//        }.flowOn(dispatcher)

}