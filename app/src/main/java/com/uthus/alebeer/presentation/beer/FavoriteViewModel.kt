package com.uthus.alebeer.presentation.beer

import androidx.lifecycle.ViewModel
import com.uthus.alebeer.domain.usecase.GetBeersUseCase
import com.uthus.alebeer.domain.usecase.SaveFavoriteUseCase

class FavoriteViewModel(
    private val getBeersUseCase: GetBeersUseCase,
    private val saveFavoriteUseCase: SaveFavoriteUseCase
) : ViewModel() {

}