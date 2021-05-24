package com.app.myapplication.activity.homepage.di

import androidx.appcompat.app.AppCompatActivity
import com.app.myapplication.activity.homepage.mvp.HomeAdapter
import com.app.myapplication.activity.homepage.mvp.HomePageModel
import com.app.myapplication.activity.homepage.mvp.HomePagePresenter
import com.app.myapplication.activity.homepage.mvp.HomePageView
import com.app.myapplication.dagger.AppActivity
import dagger.Module
import dagger.Provides

@Module
class HomePageModule(private val appCompatActivity: AppCompatActivity) {


    @AppActivity
    @Provides
    fun homeView(): HomePageView {
        return HomePageView(appCompatActivity)
    }

    @AppActivity
    @Provides
    fun homePresenter(homePageView: HomePageView, homePageModel: HomePageModel): HomePagePresenter {
        return HomePagePresenter(homePageView, homePageModel,appCompatActivity)
    }

    @AppActivity
    @Provides
    fun homeModel(): HomePageModel {
        return HomePageModel(appCompatActivity)
    }


}