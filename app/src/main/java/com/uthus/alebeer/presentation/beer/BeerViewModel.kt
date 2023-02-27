package com.uthus.alebeer.presentation.beer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uthus.alebeer.data.model.BeerModel
import com.uthus.alebeer.domain.usecase.GetBeersUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class BeerViewModel(private val getBeersUseCase: GetBeersUseCase) : ViewModel() {

    private val _listBeerModel = MutableLiveData<List<BeerModel>>()
    val listBeerModel : LiveData<List<BeerModel>> = _listBeerModel
    fun getBeers()  = viewModelScope.launch {
        getBeersUseCase.execute().collect {
            _listBeerModel.postValue(it)
        }
    }
}