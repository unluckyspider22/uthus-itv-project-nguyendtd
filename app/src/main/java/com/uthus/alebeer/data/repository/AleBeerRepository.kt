package com.uthus.alebeer.data.repository

import com.uthus.alebeer.data.datasource.AleBeerRemoteDataSource
import com.uthus.alebeer.data.model.BeerModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

interface AleBeerRepository {
    fun getBeers() : Flow<List<BeerModel>>
}

class AleBeerRepositoryImpl(private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
                            private val aleBeerRemoteDataSource: AleBeerRemoteDataSource)
    : AleBeerRepository {
    override fun getBeers(): Flow<List<BeerModel>> = aleBeerRemoteDataSource.getBeers().flowOn(dispatcher)

}