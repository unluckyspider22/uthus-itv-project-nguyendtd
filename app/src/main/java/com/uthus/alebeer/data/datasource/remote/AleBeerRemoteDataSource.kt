package com.uthus.alebeer.data.datasource.remote

import com.uthus.alebeer.data.api.AleBeerApi
import com.uthus.alebeer.data.model.BeerModel
import com.uthus.alebeer.data.model.ResponseModel
import com.uthus.alebeer.util.network.AleBeerNetwork
import com.uthus.alebeer.util.statemanagement.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface AleBeerRemoteDataSource {
    fun getBeers() : Flow<ResultState<List<BeerModel>?>>
}
private const val TEMP_PAGE = 1
private const val LIMIT = 20

class AleBeerRemoteDataSourceImpl : AleBeerRemoteDataSource {
    override fun getBeers(): Flow<ResultState<List<BeerModel>?>>
        = flow {
        val retrofitService : AleBeerApi = AleBeerNetwork.retrofit.create(AleBeerApi::class.java)
        val responseModel: ResponseModel<List<BeerModel>> = retrofitService.getBeers(page = TEMP_PAGE, limit = LIMIT)
        //TODO: Handle exception case
        emit(ResultState.Success(responseModel.data))
    }


}