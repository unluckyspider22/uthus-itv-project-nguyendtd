package com.uthus.alebeer.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.uthus.alebeer.domain.usecase.GetBeersUseCase
import com.uthus.alebeer.domain.usecase.SaveFavoriteUseCase
import com.uthus.alebeer.presentation.beer.BeerViewModel
import com.uthus.alebeer.presentation.beer.FavoriteViewModel

class BeerViewModelFactory(
    private val getBeersUseCase: GetBeersUseCase,
    private val saveFavoriteUseCase: SaveFavoriteUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BeerViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BeerViewModel(getBeersUseCase,saveFavoriteUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class FavoriteViewModelFactory(
    private val getBeersUseCase: GetBeersUseCase,
    private val saveFavoriteUseCase: SaveFavoriteUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BeerViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavoriteViewModel(getBeersUseCase,saveFavoriteUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}