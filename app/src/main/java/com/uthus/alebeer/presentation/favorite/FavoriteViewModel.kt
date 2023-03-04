package com.uthus.alebeer.presentation.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uthus.alebeer.data.model.BeerModel
import com.uthus.alebeer.domain.usecase.DeleteFavoriteBeerUseCase
import com.uthus.alebeer.domain.usecase.GetLocalBeersUseCase
import com.uthus.alebeer.domain.usecase.SaveFavoriteUseCase
import com.uthus.alebeer.domain.usecase.UpdateFavoriteBeerUseCase
import com.uthus.alebeer.util.statemanagement.ResultState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val getLocalBeersUseCase: GetLocalBeersUseCase,
    private val deleteFavoriteBeerUseCase: DeleteFavoriteBeerUseCase,
    private val updateFavoriteBeerUseCase: UpdateFavoriteBeerUseCase
) : ViewModel() {

    private val _listBeers = MutableLiveData<ResultState<List<BeerModel>>>()
    val listBeers: LiveData<ResultState<List<BeerModel>>> = _listBeers

    private val _onDeleteBeer = MutableLiveData<ResultState<Int?>>()
    val onDeleteBeer: LiveData<ResultState<Int?>> = _onDeleteBeer

    private val _onUpdateBeer = MutableLiveData<ResultState<Int?>>()
    val onUpdateBeer: LiveData<ResultState<Int?>> = _onUpdateBeer

    var currentDeletePos: Int = -1
    var currentUpdatePos: Int = -1
    var noteChanged: String = ""
    fun getLocalBeers() {
        viewModelScope.launch {
            getLocalBeersUseCase.execute().collect {
                _listBeers.postValue(it)
            }
        }
    }

    fun deleteFavoriteBeer(id: Long, position: Int) {
        currentDeletePos = position
        viewModelScope.launch {
            deleteFavoriteBeerUseCase.execute(id).collect {
                _onDeleteBeer.postValue(it)
            }
        }
    }

    fun updateFavoriteBeer(note: String, id: Long, position: Int) {
        currentUpdatePos = position
        noteChanged = note
        viewModelScope.launch {
            updateFavoriteBeerUseCase.execute(note, id).collect {
                _onUpdateBeer.postValue(it)
            }
        }
    }
}