package com.uthus.alebeer.data.datasource.remote

import com.uthus.alebeer.data.api.AleBeerApi
import com.uthus.alebeer.data.model.BeerModel
import com.uthus.alebeer.data.model.ResponseModel
import com.uthus.alebeer.util.network.AleBeerNetwork
import com.uthus.alebeer.util.statemanagement.ResultState
import com.uthus.alebeer.util.statemanagement.StateHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface AleBeerRemoteDataSource {
    fun getBeers(): Flow<ResultState<ResponseModel<List<BeerModel>>>>
}

private const val TEMP_PAGE = 1
private const val LIMIT = 20

class AleBeerRemoteDataSourceImpl : AleBeerRemoteDataSource {
    private val apiCall: AleBeerApi = AleBeerNetwork.retrofit.create(AleBeerApi::class.java)

    override fun getBeers(): Flow<ResultState<ResponseModel<List<BeerModel>>>> = flow {
        val resultState: ResultState<ResponseModel<List<BeerModel>>> =
            StateHandler.execute { apiCall.getBeers(page = TEMP_PAGE, limit = LIMIT) }
        emit(resultState)
    }


}