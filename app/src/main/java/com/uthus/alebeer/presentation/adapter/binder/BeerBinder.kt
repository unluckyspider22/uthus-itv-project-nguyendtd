package com.uthus.alebeer.presentation.adapter.binder


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.uthus.alebeer.R
import com.uthus.alebeer.data.model.BeerModel
import com.uthus.alebeer.databinding.BeerItemLayoutBinding
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class BeerBinder(private val buttonClickedListener: ButtonClickedListener) :
    ItemBinder<BeerModel, BeerBinder.BeerViewHolder>() {

    inner class BeerViewHolder(private val binding: BeerItemLayoutBinding) :
        ItemViewHolder<BeerModel>(binding.root) {
        val context: Context = binding.root.context
        fun bind(beerModel: BeerModel, buttonClickedListener: ButtonClickedListener) =
            with(binding) {
                tvName.text = beerModel?.name
                tvPrice.text = beerModel?.price
                Glide.with(context)
                    .load(beerModel?.image)
                    .into(ivBeer)
                btnRight.setOnClickListener {
                    if(!etNote.text.isNullOrEmpty()) {
                        buttonClickedListener.onButtonSaveClick(
                            bindingAdapterPosition,
                            beerModel.also {
                                it?.note = etNote.text.toString()
                            })
                        btnRight.setText(R.string.saving)
                    }
                }
            }
    }

    override fun bindViewHolder(holder: BeerViewHolder, item: BeerModel) {
        holder?.bind(item, buttonClickedListener)
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

interface ButtonClickedListener {
    fun onButtonSaveClick(position: Int, beerModel: BeerModel)
}