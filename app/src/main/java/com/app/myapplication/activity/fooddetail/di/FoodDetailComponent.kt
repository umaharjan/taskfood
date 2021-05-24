package com.app.myapplication.activity.fooddetail.di

import com.app.myapplication.activity.fooddetail.FoodDetailActivity
import com.app.myapplication.dagger.AppActivity
import com.app.myapplication.dagger.AppComponent
import dagger.Component

@AppActivity
@Component(modules=[(FoodDetailModule::class)], dependencies=[(AppComponent::class)])
interface FoodDetailComponent {
    abstract fun inject(foodDetailActivity: FoodDetailActivity)
}