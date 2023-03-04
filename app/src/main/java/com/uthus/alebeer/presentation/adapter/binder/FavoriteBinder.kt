package com.uthus.alebeer.presentation.adapter.binder

import android.content.Context
import android.text.Editable
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.uthus.alebeer.R
import com.uthus.alebeer.data.model.BeerModel
import com.uthus.alebeer.databinding.BeerItemLayoutBinding
import com.uthus.alebeer.databinding.FavoriteItemLayoutBinding
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class FavoriteBinder(private val onButtonClickedListener: OnButtonClickedListener) :
    ItemBinder<BeerModel, FavoriteBinder.FavoriteViewHolder>() {

    inner class FavoriteViewHolder(private val binding: FavoriteItemLayoutBinding) :
        ItemViewHolder<BeerModel>(binding.root) {
        private val context: Context = binding.root.context
        fun bind(beerModel: BeerModel, onButtonClickedListener: OnButtonClickedListener) =
            with(binding) {
                tvName.text = beerModel?.name
                tvPrice.text = beerModel?.price
                Glide.with(context)
                    .load(beerModel?.image)
                    .into(ivBeer)
                etNote.setText(beerModel.note)
                btnUpdate.setOnClickListener {
                    if (!etNote.text.isNullOrEmpty()) {
                        onButtonClickedListener.onButtonUpdateClicked(
                            bindingAdapterPosition,
                            beerModel.also {
                                it?.note = etNote.text.toString()
                            })
                        btnUpdate.setText(R.string.saving)
                    }
                }
                btnDelete.setOnClickListener {
                    onButtonClickedListener.onButtonDeleteClick(bindingAdapterPosition, beerModel)
                }
            }
    }

    override fun bindViewHolder(holder: FavoriteViewHolder, item: BeerModel) {
        holder?.bind(item, onButtonClickedListener)
    }

    override fun createViewHolder(parent: ViewGroup?): FavoriteViewHolder {
        return FavoriteViewHolder(
            FavoriteItemLayoutBinding.inflate(
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

