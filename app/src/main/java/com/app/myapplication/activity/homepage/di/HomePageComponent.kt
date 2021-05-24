package com.app.myapplication.activity.homepage.di

import com.app.myapplication.activity.homepage.HomePageActivity
import com.app.myapplication.dagger.AppActivity
import com.app.myapplication.dagger.AppComponent
import dagger.Component

@AppActivity
@Component(modules=[(HomePageModule::class)], dependencies=[(AppComponent::class)])

interface HomePageComponent {
    fun inject(homePageActivity: HomePageActivity)
}