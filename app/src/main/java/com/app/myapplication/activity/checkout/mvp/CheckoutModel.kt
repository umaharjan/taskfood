package com.app.myapplication.activity.checkout.mvp

import androidx.appcompat.app.AppCompatActivity
import com.app.myapplication.activity.summery.SummaryActivity

class CheckoutModel(private val appCompatActivity: AppCompatActivity) {

    fun getSummeryView(price:String,  discount:String){
        SummaryActivity.start(appCompatActivity,price,discount)
    }

    fun getFinished(){
        appCompatActivity.finish()
    }
}