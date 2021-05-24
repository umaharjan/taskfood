package com.app.myapplication.activity.cartlist.mvp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.app.myapplication.activity.checkout.CheckoutActivity
import com.app.myapplication.activity.homepage.HomePageActivity

class CartListModel(private val appCompatActivity: AppCompatActivity) {

    fun getCheckView(price:String,  discount:String){
        CheckoutActivity.start(appCompatActivity,price,discount)
    }

    fun getHomeView() {
        val intent = Intent(appCompatActivity, HomePageActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        intent.putExtra("EXIT", true)
        appCompatActivity.startActivity(intent)
        appCompatActivity.finish()
    }

    fun getFinished(){
        appCompatActivity.finish()
    }
}