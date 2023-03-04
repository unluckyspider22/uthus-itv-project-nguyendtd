package com.uthus.alebeer.presentation.beer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uthus.alebeer.data.model.BeerModel
import com.uthus.alebeer.domain.usecase.GetBeersUseCase
import com.uthus.alebeer.domain.usecase.SaveFavoriteUseCase
import com.uthus.alebeer.util.Action
import com.uthus.alebeer.util.EventBus
import com.uthus.alebeer.util.statemanagement.ResultState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class BeerViewModel(
    private val getBeersUseCase: GetBeersUseCase,
    private val saveFavoriteUseCase: SaveFavoriteUseCase
) : ViewModel() {

    private val _listBeerModel = MutableLiveData<ResultState<List<BeerModel>>>()
    val listBeerModel: LiveData<ResultState<List<BeerModel>>> = _listBeerModel
    var currentSavingPosition: Int = -1
    private val _onSaveFavorite = MutableLiveData<ResultState<Long?>>()
    val onSaveFavorite: LiveData<ResultState<Long?>> = _onSaveFavorite
    fun getBeers() = viewModelScope.launch {
        getBeersUseCase.execute().collect {
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

    fun publishOnInsert() {
        viewModelScope.launch {
            EventBus.publishEvent(Action.SAVE)
        }
    }

}