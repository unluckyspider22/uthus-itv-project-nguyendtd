package com.uthus.alebeer.domain.usecase

import com.uthus.alebeer.data.model.BeerModel
import com.uthus.alebeer.data.repository.AleBeerRepository
import com.uthus.alebeer.data.repository.AleBeerRepositoryImpl
import com.uthus.alebeer.util.statemanagement.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

interface GetBeersUseCase {
    fun execute() : Flow<ResultState<List<BeerModel>?>>
}

class GetBeersUseCaseImpl : GetBeersUseCase {
    private val repository: AleBeerRepository = AleBeerRepositoryImpl()
    override fun execute(): Flow<ResultState<List<BeerModel>?>> = repository.getBeers()
}