package com.uthus.alebeer.domain.usecase

import com.uthus.alebeer.data.model.BeerModel
import com.uthus.alebeer.data.model.ResponseModel
import com.uthus.alebeer.data.repository.AleBeerRepository
import com.uthus.alebeer.domain.mapper.BeerEntityToBeerModelMapper
import com.uthus.alebeer.domain.mapper.BeerEntityToBeerModelMapperImpl
import com.uthus.alebeer.util.statemanagement.ResultState
import com.uthus.alebeer.util.statemanagement.map
import kotlinx.coroutines.flow.*

interface GetBeersUseCase {
    fun execute(): Flow<ResultState<List<BeerModel>>>
}

class GetBeersUseCaseImpl(private val repository: AleBeerRepository) : GetBeersUseCase {
    private val mapper: BeerEntityToBeerModelMapper = BeerEntityToBeerModelMapperImpl()
    override fun execute(): Flow<ResultState<List<BeerModel>>> =

        repository.getBeers().combine(repository.getLocalBeers()) { remote, local ->
            val finalBeerList = mutableListOf<BeerModel>()
            if (remote is ResultState.Success && local is ResultState.Success) {
                val localBeerList = local.data.map {
                    mapper.transform(it)
                }
                finalBeerList.addAll(localBeerList)
                remote.data.data.forEach { remoteItem ->
                    if (!finalBeerList.any { finalItem ->
                            finalItem.id == remoteItem.id
                        }) {
                        finalBeerList.add(remoteItem)
                    }
                }
                ResultState.Success(finalBeerList)
            } else if (remote is ResultState.Success && local is ResultState.Error) {
                remote.map {
                    it.data
                }
            } else {
                ResultState.Success(emptyList())
            }
        }
}