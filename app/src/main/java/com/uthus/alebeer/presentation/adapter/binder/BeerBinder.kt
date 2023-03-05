package com.uthus.alebeer.presentation.adapter.binder


import android.content.Context
import android.graphics.drawable.Drawable
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.uthus.alebeer.R
import com.uthus.alebeer.data.model.BeerModel
import com.uthus.alebeer.databinding.BeerItemLayoutBinding
import com.uthus.alebeer.util.ToastExt
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder
import java.text.SimpleDateFormat
import java.util.*


class BeerBinder(private val buttonClickedListener: OnButtonClickedListener) :
    ItemBinder<BeerModel, BeerBinder.BeerViewHolder>() {


    override fun bindViewHolder(holder: BeerViewHolder, item: BeerModel) {
        holder.bind(item, buttonClickedListener)
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


    private val DatePattern = "HH:mm:ss dd-MM"

    inner class BeerViewHolder(
        private val binding: BeerItemLayoutBinding,
    ) :
        ItemViewHolder<BeerModel>(binding.root) {
        private val context: Context = binding.root.context
        private var timer: CountDownTimer? = null


        fun bind(beerModel: BeerModel, buttonClickedListener: OnButtonClickedListener) =
            with(binding) {

                //Handle title
                tvName.text = beerModel.name
                tvPrice.text = beerModel.price
                Log.d("nguyeen", tvPrice.text.toString())
                Log.d("nguyeen", etNote.text.toString())
                //Handle image
                Glide.with(context).load(beerModel.image).error(R.drawable.beer_default_img)
                    .placeholder(R.drawable.beer_default_img)
                    .into(ivBeer)

                        //Handle Save button
                    handleSaveButton(context, beerModel, buttonClickedListener)

                //Handle timer
                handleSaleOffTime(beerModel)
            }

        private fun BeerItemLayoutBinding.handleSaleOffTime(beerModel: BeerModel) {
            var pointOfExpired: Long = beerModel.saleOffTime
            val currentTime: Long = Date().time
            var expiryTime: Long = pointOfExpired - currentTime

            if (timer != null) {
                timer!!.cancel()
            }
            timer = object : CountDownTimer(expiryTime, 500) {
                override fun onTick(millisUntilFinished: Long) {
                    val sdf = SimpleDateFormat(DatePattern)
                    tvCountdown.text = context.resources.getText(R.string.sale_off_in)
                        .toString() + "\n" + sdf.format(Date(millisUntilFinished))
                }

                override fun onFinish() {
                    tvCountdown.setText(R.string.sale_off)
                }
            }.start()
        }

        private fun BeerItemLayoutBinding.handleSaveButton(
            context: Context,
            beerModel: BeerModel,
            buttonClickedListener: OnButtonClickedListener
        ) {
            if (beerModel.isSaved) {
                btnRight.visibility = View.GONE
                etNote.setText("")
                etNote.isEnabled = false
                etNote.setHint(R.string.can_not_edit)
            } else {
                btnRight.setOnClickListener {
                    if (!etNote.text.isNullOrEmpty()) {
                        buttonClickedListener.onButtonSaveClick(
                            bindingAdapterPosition,
                            beerModel.also {
                                it.note = etNote.text.toString()
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

