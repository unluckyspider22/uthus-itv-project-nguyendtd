package com.uthus.alebeer.domain.usecase

import com.uthus.alebeer.data.repository.AleBeerRepository
import com.uthus.alebeer.util.statemanagement.ResultState
import kotlinx.coroutines.flow.Flow

interface DeleteFavoriteBeerUseCase {
    fun execute(id: Long) : Flow<ResultState<Int?>>
}

class DeleteFavoriteBeerUseCaseImpl(private val repository: AleBeerRepository) : DeleteFavoriteBeerUseCase {
    override fun execute(id: Long): Flow<ResultState<Int?>> = repository.deleteFavoriteBeer(id)
}