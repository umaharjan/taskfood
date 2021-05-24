package com.app.myapplication.activity.fooddetail


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler

import androidx.appcompat.app.AppCompatActivity
import com.app.myapplication.activity.fooddetail.FoodDetailActivity.catInfo.info
import com.app.myapplication.activity.fooddetail.di.DaggerFoodDetailComponent
import com.app.myapplication.activity.fooddetail.di.FoodDetailModule
import com.app.myapplication.activity.fooddetail.mvp.FoodDetailPresenter
import com.app.myapplication.activity.fooddetail.mvp.FoodDetailView
import com.app.myapplication.activity.homepage.di.DaggerHomePageComponent
import com.app.myapplication.activity.homepage.di.HomePageModule
import com.app.myapplication.activity.homepage.mvp.HomePagePresenter
import com.app.myapplication.activity.homepage.mvp.HomePageView
import com.app.myapplication.dagger.AppApplication
import com.app.myapplication.databasemanager.foodModelInfo
import javax.inject.Inject


class FoodDetailActivity : AppCompatActivity(){

    @Inject
    lateinit var  foodDetailView: FoodDetailView
    @Inject
    lateinit var  foodDetailPresenter: FoodDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appApplication=AppApplication()
        DaggerFoodDetailComponent.builder()
                .appComponent(appApplication.get(this).appComponent)
                .foodDetailModule(FoodDetailModule(this))
                .build()
                .inject(this)
        setContentView(foodDetailView)
        foodDetailPresenter.onCreateView(info!!)
    }



    object catInfo{
        var info:foodModelInfo?=null
    }
    companion object {
        fun start(context: Context ,foodInfo:foodModelInfo) {
            info=foodInfo
            context.startActivity(Intent(context, FoodDetailActivity::class.java))
        }
    }


}