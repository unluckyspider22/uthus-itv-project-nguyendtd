package com.uthus.alebeer.presentation.adapter.binder


import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.uthus.alebeer.data.model.BeerModel
import com.uthus.alebeer.databinding.BeerItemLayoutBinding
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class BeerBinder : ItemBinder<BeerModel, BeerBinder.BeerViewHolder>() {

    inner class BeerViewHolder(private val binding: BeerItemLayoutBinding) :
        ItemViewHolder<BeerModel>(binding.root) {
        fun bind(beerModel: BeerModel?) = with(binding) {
            tvName.text = beerModel?.name
            tvPrice.text = beerModel?.price
            Glide.with(binding.root.context).load(beerModel?.image).into(ivBeer)

        }
    }

    override fun bindViewHolder(holder: BeerViewHolder?, item: BeerModel?) {
        holder?.bind(item)
    }

    override fun createViewHolder(parent: ViewGroup?): BeerViewHolder {
        return BeerViewHolder(
            BeerItemLayoutBinding.inflate(
                LayoutInflater.from(
                    parent?.context
                ), parent, false
            )
        )
    }

    override fun canBindData(item: Any?): Boolean {
        return item is BeerModel
    }

}