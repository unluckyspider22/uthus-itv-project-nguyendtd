package com.uthus.alebeer.presentation.adapter.binder


import android.view.ViewGroup
import com.uthus.alebeer.data.model.BeerModel
import com.uthus.alebeer.databinding.BeerItemLayoutBinding
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class BeerBinder : ItemBinder<BeerModel, BeerBinder.BeerViewHolder>() {

    inner class BeerViewHolder(private val binding: BeerItemLayoutBinding) : ItemViewHolder<BeerModel>(binding.root) {
        fun bind(beerModel: BeerModel) {

        }
    }

    override fun bindViewHolder(holder: BeerViewHolder?, item: BeerModel?) {
        TODO("Not yet implemented")
    }

    override fun createViewHolder(parent: ViewGroup?): BeerViewHolder {
        TODO("Not yet implemented")
    }

    override fun canBindData(item: Any?): Boolean {
        TODO("Not yet implemented")
    }
}