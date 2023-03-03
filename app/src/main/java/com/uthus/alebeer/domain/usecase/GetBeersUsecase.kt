package com.uthus.alebeer.domain.usecase

import com.uthus.alebeer.data.model.BeerModel
import com.uthus.alebeer.data.model.ResponseModel
import com.uthus.alebeer.data.repository.AleBeerRepository
import com.uthus.alebeer.util.statemanagement.ResultState
import kotlinx.coroutines.flow.*

interface GetBeersUseCase {
    fun execute(): Flow<ResultState<ResponseModel<List<BeerModel>>>>
}

class GetBeersUseCaseImpl(private val repository: AleBeerRepository) : GetBeersUseCase {

    override fun execute(): Flow<ResultState<ResponseModel<List<BeerModel>>>> {
        return repository.getBeers()
    }
}