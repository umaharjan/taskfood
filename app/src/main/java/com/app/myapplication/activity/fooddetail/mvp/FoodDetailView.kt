package com.app.myapplication.activity.fooddetail.mvp


import android.graphics.Paint
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.app.myapplication.R
import com.app.myapplication.databasemanager.foodModelInfo
import com.jakewharton.rxbinding2.view.RxView
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import kotlinx.android.synthetic.main.food_detail_layout.view.*

class FoodDetailView(private val appCompatActivity: AppCompatActivity) :
    FrameLayout(appCompatActivity) {

    init {
        View.inflate(appCompatActivity, R.layout.food_detail_layout, this)
    }


    fun getInfo(info: foodModelInfo) {

        appCompatActivity.runOnUiThread(Runnable {
            tvDiscount.visibility=View.GONE
            try {
                Picasso.with(appCompatActivity).load(info.image!!).into(ivImage)
            } catch (ex: Exception) {

            }

            tvName.text=info.name
            when {
                info.discount == 0.0 -> {
                    rlDiscountRibbon.visibility=View.GONE
                    tvDiscount.visibility=View.GONE
                    tvVariantPrice.text="Rs. ${info.price}"
                }
                else -> {
                    rlDiscountRibbon.visibility=View.VISIBLE
                    tvDiscountPercentage.text="${info!!.discount}%"
                    var price=info!!.price!! - info!!.discount_price!!
                    tvDiscount.text="Rs. ${info.price}"
                    tvDiscount.paintFlags=tvDiscount.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                    tvDiscount.visibility=View.VISIBLE
                    tvVariantPrice.text="Rs. ${price}"

                }
            }
        })


    }

    fun cartObserable(): Observable<Any> {
        return  RxView.clicks(llAddtoCart)
    }

    fun getBackObserable():Observable<Any>{
        return  RxView.clicks(ivBack)
    }
}