package com.uthus.alebeer.domain.usecase

import com.uthus.alebeer.data.model.BeerModel
import com.uthus.alebeer.data.repository.AleBeerRepository
import com.uthus.alebeer.data.repository.AleBeerRepositoryImpl
import com.uthus.alebeer.domain.mapper.BeerModelToBeerEntityMapperImpl
import com.uthus.alebeer.util.statemanagement.ResultState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

interface SaveFavoriteUseCase {
    fun execute(beerModel: BeerModel): Flow<ResultState<Long?>>
}

class SaveFavoriteUseCaseImpl(
    private val repository: AleBeerRepository,
) : SaveFavoriteUseCase {
    private val mapper = BeerModelToBeerEntityMapperImpl()

    override fun execute(beerModel: BeerModel): Flow<ResultState<Long?>> =
        repository.saveFavoriteToLocal(mapper.transform(beerModel))
}