package com.uthus.alebeer.domain.usecase

import com.uthus.alebeer.data.model.BeerModel
import com.uthus.alebeer.data.repository.AleBeerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

interface GetBeersUseCase {
    fun execute() : Flow<List<BeerModel>>
}

class GetBeersUseCaseImpl(private val repository: AleBeerRepository) : GetBeersUseCase {
    override fun execute(): Flow<List<BeerModel>> = repository.getBeers()
}