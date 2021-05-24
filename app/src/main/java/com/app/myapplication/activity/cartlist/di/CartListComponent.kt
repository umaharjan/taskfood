package com.app.myapplication.activity.cartlist.di

import com.app.myapplication.activity.cartlist.CartListActivity
import com.app.myapplication.activity.fooddetail.di.FoodDetailModule
import com.app.myapplication.dagger.AppActivity
import com.app.myapplication.dagger.AppComponent
import dagger.Component

@AppActivity
@Component(modules=[(CartListModule::class)], dependencies=[(AppComponent::class)])

interface CartListComponent {
    abstract fun inject(cartListActivity: CartListActivity)
}