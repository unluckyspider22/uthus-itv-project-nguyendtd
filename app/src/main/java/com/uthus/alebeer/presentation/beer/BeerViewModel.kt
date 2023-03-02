package com.uthus.alebeer.presentation.beer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uthus.alebeer.data.model.BeerModel
import com.uthus.alebeer.domain.usecase.GetBeersUseCase
import com.uthus.alebeer.domain.usecase.GetBeersUseCaseImpl
import com.uthus.alebeer.util.statemanagement.ResultState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class BeerViewModel : ViewModel() {
    private val getBeersUseCase: GetBeersUseCase = GetBeersUseCaseImpl()
    private val _listBeerModel = MutableLiveData<ResultState<List<BeerModel>?>>()
    val listBeerModel : LiveData<ResultState<List<BeerModel>?>> = _listBeerModel
    fun getBeers()  = viewModelScope.launch {
        getBeersUseCase.execute().collect {
            _listBeerModel.postValue(it)
        }
    }
}