package com.app.myapplication.activity.checkout.di

import com.app.myapplication.activity.cartlist.di.CartListModule
import com.app.myapplication.activity.checkout.CheckoutActivity
import com.app.myapplication.dagger.AppActivity
import com.app.myapplication.dagger.AppComponent
import dagger.Component

@AppActivity
@Component(modules=[(CheckoutModule::class)], dependencies=[(AppComponent::class)])
interface CheckoutComponent {
    abstract fun inject(checkoutActivity: CheckoutActivity)

}