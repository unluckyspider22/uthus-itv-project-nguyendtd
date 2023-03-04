package com.uthus.alebeer.presentation.adapter.binder

import com.uthus.alebeer.data.model.BeerModel

interface OnButtonClickedListener {
    fun onButtonSaveClick(position: Int, beerModel: BeerModel)
    fun onButtonDeleteClick(position: Int, beerModel: BeerModel)
    fun onButtonUpdateClicked(position: Int, beerModel: BeerModel)
}