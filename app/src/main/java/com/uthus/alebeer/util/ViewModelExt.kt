package com.uthus.alebeer.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.uthus.alebeer.domain.usecase.*
import com.uthus.alebeer.presentation.beer.BeerViewModel
import com.uthus.alebeer.presentation.favorite.FavoriteViewModel

class BeerViewModelFactory(
    private val getBeersUseCase: GetBeersUseCase,
//    private val getLocalBeersUseCase: GetLocalBeersUseCase,
    private val saveFavoriteUseCase: SaveFavoriteUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BeerViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BeerViewModel(getBeersUseCase, saveFavoriteUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class FavoriteViewModelFactory(
    private val getLocalBeersUseCase: GetLocalBeersUseCase,
    private val deleteFavoriteBeerUseCase: DeleteFavoriteBeerUseCase,
    private val updateFavoriteBeerUseCase: UpdateFavoriteBeerUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavoriteViewModel(
                getLocalBeersUseCase,
                deleteFavoriteBeerUseCase,
                updateFavoriteBeerUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}