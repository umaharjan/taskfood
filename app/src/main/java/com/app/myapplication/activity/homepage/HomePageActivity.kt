package com.app.myapplication.activity.homepage


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler

import androidx.appcompat.app.AppCompatActivity
import com.app.myapplication.activity.homepage.di.DaggerHomePageComponent
import com.app.myapplication.activity.homepage.di.HomePageModule
import com.app.myapplication.activity.homepage.mvp.HomePagePresenter
import com.app.myapplication.activity.homepage.mvp.HomePageView
import com.app.myapplication.dagger.AppApplication
import javax.inject.Inject


class HomePageActivity : AppCompatActivity(){

    @Inject
    lateinit var  homePageView: HomePageView
    @Inject
    lateinit var  homePagePresenter: HomePagePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appApplication=AppApplication()
        DaggerHomePageComponent.builder()
                .appComponent(appApplication.get(this).appComponent)
                .homePageModule(HomePageModule(this))
                .build()
                .inject(this)
        setContentView(homePageView)
        homePagePresenter.onCreateView()
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, HomePageActivity::class.java))
        }
    }


}