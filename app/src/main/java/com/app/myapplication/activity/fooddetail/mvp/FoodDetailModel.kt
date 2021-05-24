package com.app.myapplication.activity.fooddetail.mvp

import androidx.appcompat.app.AppCompatActivity
import com.app.myapplication.activity.cartlist.CartListActivity

class FoodDetailModel(private val appCompatActivity: AppCompatActivity) {

    fun getCartlistView(){
        CartListActivity.start(appCompatActivity)
    }

    fun getFinished(){
        appCompatActivity.finish()
    }
}