package com.uthus.alebeer.presentation.adapter.binder


import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.uthus.alebeer.R
import com.uthus.alebeer.data.model.BeerModel
import com.uthus.alebeer.databinding.BeerItemLayoutBinding
import com.uthus.alebeer.util.ToastExt
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class BeerBinder(private val buttonClickedListener: OnButtonClickedListener) :
    ItemBinder<BeerModel, BeerBinder.BeerViewHolder>() {


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


    inner class BeerViewHolder(private val binding: BeerItemLayoutBinding) :
        ItemViewHolder<BeerModel>(binding.root) {
        private val context: Context = binding.root.context
        fun bind(beerModel: BeerModel, buttonClickedListener: OnButtonClickedListener) =
            with(binding) {
                //Handle title
                tvName.text = beerModel?.name
                tvPrice.text = beerModel?.price
                Log.d("nguyeen", tvPrice.text.toString())
                Log.d("nguyeen", etNote.text.toString())
                //Handle image
                Glide.with(context)
                    .load(beerModel?.image)
                    .into(ivBeer)
                //Handle Save button
                handleSaveButton(context, beerModel, buttonClickedListener)
            }

        private fun BeerItemLayoutBinding.handleSaveButton(
            context: Context,
            beerModel: BeerModel,
            buttonClickedListener: OnButtonClickedListener
        ) {
            if (beerModel.isSaved) {
                btnRight.visibility = View.GONE
                etNote.isEnabled = false
                etNote.setHint(R.string.can_not_edit)
            } else {
                btnRight.setOnClickListener {
                    if (!etNote.text.isNullOrEmpty()) {
                        buttonClickedListener.onButtonSaveClick(
                            bindingAdapterPosition,
                            beerModel.also {
                                it?.note = etNote.text.toString()
                            })
                        btnRight.setText(R.string.saving)
                    } else {
                        ToastExt.showToast(context, R.string.please_edit)
                    }
                }
            }
        }
    }


}

