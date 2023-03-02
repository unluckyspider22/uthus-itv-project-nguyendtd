package com.uthus.alebeer.data.repository

import com.uthus.alebeer.data.datasource.remote.AleBeerRemoteDataSource
import com.uthus.alebeer.data.datasource.remote.AleBeerRemoteDataSourceImpl
import com.uthus.alebeer.data.model.BeerModel
import com.uthus.alebeer.util.statemanagement.ResultState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

interface AleBeerRepository {
    fun getBeers() : Flow<ResultState<List<BeerModel>?>>
}

class AleBeerRepositoryImpl(private val dispatcher: CoroutineDispatcher = Dispatchers.IO)
    : AleBeerRepository {
    private val aleBeerRemoteDataSource : AleBeerRemoteDataSource = AleBeerRemoteDataSourceImpl()
    override  fun getBeers(): Flow<ResultState<List<BeerModel>?>> = aleBeerRemoteDataSource.getBeers().flowOn(dispatcher)

}