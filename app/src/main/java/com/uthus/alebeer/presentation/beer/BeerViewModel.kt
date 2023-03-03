package com.uthus.alebeer.presentation.beer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uthus.alebeer.data.model.BeerModel
import com.uthus.alebeer.data.model.ResponseModel
import com.uthus.alebeer.data.repository.BeerGroup
import com.uthus.alebeer.domain.usecase.GetBeersUseCase
import com.uthus.alebeer.domain.usecase.SaveFavoriteUseCase
import com.uthus.alebeer.util.statemanagement.ResultState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class BeerViewModel(
    private val getBeersUseCase: GetBeersUseCase,
    private val saveFavoriteUseCase: SaveFavoriteUseCase
) : ViewModel() {
    //    private val getBeersUseCase: GetBeersUseCase = GetBeersUseCaseImpl()
//    private val saveFavoriteUseCase: SaveFavoriteUseCase = SaveFavoriteUseCaseImpl()
    private val _listBeerModel = MutableLiveData<ResultState<ResponseModel<List<BeerModel>>>>()
    val listBeerModel: LiveData<ResultState<ResponseModel<List<BeerModel>>>> = _listBeerModel
    var currentSavingPosition: Int = -1
    private val _onSaveFavorite = MutableLiveData<ResultState<Long?>>()
    val onSaveFavorite: LiveData<ResultState<Long?>> = _onSaveFavorite
    fun getBeers() = viewModelScope.launch {
        getBeersUseCase.execute(beerGroup = BeerGroup.ALL).collect {
            _listBeerModel.postValue(it)
        }
    }

    fun saveFavorite(beerModel: BeerModel, position: Int) {
        currentSavingPosition = position
        viewModelScope.launch {
            saveFavoriteUseCase.execute(beerModel).collect {
                    _onSaveFavorite.postValue(it)
            }
        }
    }

}