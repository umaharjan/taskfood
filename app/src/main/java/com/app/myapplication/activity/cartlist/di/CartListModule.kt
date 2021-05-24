package com.app.myapplication.activity.cartlist.di

import androidx.appcompat.app.AppCompatActivity
import com.app.myapplication.activity.cartlist.mvp.CartListModel
import com.app.myapplication.activity.cartlist.mvp.CartListPresenter
import com.app.myapplication.activity.cartlist.mvp.CartListView
import com.app.myapplication.dagger.AppActivity
import dagger.Module
import dagger.Provides

@Module
class CartListModule(private val appCompatActivity: AppCompatActivity) {

    @AppActivity
    @Provides
    fun cartView(): CartListView {
        return CartListView(appCompatActivity)
    }

    @AppActivity
    @Provides
    fun cartPresenter(cartview: CartListView, cartmodel: CartListModel): CartListPresenter {
        return CartListPresenter(cartview, cartmodel, appCompatActivity)
    }

    @AppActivity
    @Provides
    fun cartModel(): CartListModel {
        return CartListModel(appCompatActivity)
    }
}