package com.uthus.alebeer.domain.usecase

import com.uthus.alebeer.data.model.BeerModel
import com.uthus.alebeer.data.repository.AleBeerRepository
import com.uthus.alebeer.domain.mapper.BeerEntityToBeerModelMapper
import com.uthus.alebeer.domain.mapper.BeerEntityToBeerModelMapperImpl
import com.uthus.alebeer.util.statemanagement.ResultState
import com.uthus.alebeer.util.statemanagement.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface GetLocalBeersUseCase {
    fun execute(): Flow<ResultState<List<BeerModel>>>
}

class GetLocalBeersUseCaseImpl(private val repository: AleBeerRepository) : GetLocalBeersUseCase {
    private val mapper: BeerEntityToBeerModelMapper = BeerEntityToBeerModelMapperImpl()
    override fun execute(): Flow<ResultState<List<BeerModel>>> =
        repository.getLocalBeers().map { it ->
            it.map {
                it.map {
                    mapper.transform(it)
                }
            }
        }

}