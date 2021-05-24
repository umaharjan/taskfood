package com.app.myapplication.activity.checkout.di

import androidx.appcompat.app.AppCompatActivity
import com.app.myapplication.activity.checkout.mvp.CheckoutModel
import com.app.myapplication.activity.checkout.mvp.CheckoutPresenter
import com.app.myapplication.activity.checkout.mvp.CheckoutView
import com.app.myapplication.dagger.AppActivity
import dagger.Module
import dagger.Provides

@Module

class CheckoutModule(private val appCompatActivity: AppCompatActivity) {


    @AppActivity
    @Provides
    fun checkView(): CheckoutView {
        return CheckoutView(appCompatActivity)
    }

    @AppActivity
    @Provides
    fun checkPresenter(checkoutview: CheckoutView, checkmodel: CheckoutModel): CheckoutPresenter {
        return CheckoutPresenter(checkoutview, checkmodel, appCompatActivity)
    }

    @AppActivity
    @Provides
    fun checkModel(): CheckoutModel {
        return CheckoutModel(appCompatActivity)
    }
}