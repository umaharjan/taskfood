package com.app.myapplication.activity.homepage.mvp

import androidx.appcompat.app.AppCompatActivity
import com.app.myapplication.activity.cartlist.CartListActivity
import com.app.myapplication.activity.fooddetail.FoodDetailActivity
import com.app.myapplication.databasemanager.foodModelInfo

class HomePageModel(private val appCompatActivity: AppCompatActivity) {

    fun getFoodDetail(info:foodModelInfo){
        FoodDetailActivity.start(appCompatActivity,info)
    }
    fun getCartView(){
        CartListActivity.start(appCompatActivity)
    }
}