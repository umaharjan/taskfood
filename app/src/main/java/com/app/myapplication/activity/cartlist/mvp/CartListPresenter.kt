package com.app.myapplication.activity.cartlist.mvp

import androidx.appcompat.app.AppCompatActivity
import com.app.myapplication.databasemanager.CartModelInfo
import io.realm.Realm
import io.realm.kotlin.where

class CartListPresenter(
    private val cartListView: CartListView,
    private val cartListModel: CartListModel,
    private val appCompatActivity: AppCompatActivity
) {

    private lateinit var realm: Realm
    fun onCreateView() {
        realm=Realm.getDefaultInstance()
        val cartlist=realm.where<CartModelInfo>().findAll()!!
        println("cartlist===" + cartlist.size)
        when {
            cartlist.size > 0 -> {
                cartListView.hideBuy()
                cartListView.setSummary(cartlist)
                cartListView.showListItems(cartlist)
            }
            else -> {
                cartListView.hideList()
            }

        }
        onClick()
    }


    fun onClick() {
        cartListView.getCheckObserable().doOnNext { cartListModel.getCheckView(cartListView.getTotalPrice(),cartListView.getTotalDiscount()) }.subscribe()
        cartListView.getBuyObserable().doOnNext { cartListModel.getHomeView() }.subscribe()
        cartListView.getBackObserable().doOnNext { cartListModel.getFinished()}.subscribe()

    }

}