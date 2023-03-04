package com.uthus.alebeer.domain.usecase

import com.uthus.alebeer.data.repository.AleBeerRepository
import com.uthus.alebeer.util.statemanagement.ResultState
import kotlinx.coroutines.flow.Flow

interface UpdateFavoriteBeerUseCase {
    fun execute(note: String, id: Long): Flow<ResultState<Int?>>
}

class UpdateFavoriteBeerUseCaseImpl(private val repository: AleBeerRepository) :
    UpdateFavoriteBeerUseCase {
    override fun execute(note: String, id: Long): Flow<ResultState<Int?>> =
        repository.updateFavoriteBeer(note = note, id = id)

}