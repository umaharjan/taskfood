package com.app.myapplication.activity.summery.mvp

import androidx.appcompat.app.AppCompatActivity
import com.app.myapplication.databasemanager.CartModelInfo
import com.app.myapplication.databasemanager.OrderModelInfo
import io.realm.Realm
import io.realm.kotlin.where

class SummeryPresenter(private val summeryView: SummeryView, private val summeryModel: SummeryModel, private val appCompatActivity: AppCompatActivity) {
    private lateinit var realm: Realm

    fun onCreateView(price: String, discount: String){
        realm=Realm.getDefaultInstance()

        try {
            summeryView.setSummary(price.toDouble(), discount.toDouble())
        } catch (ex: Exception) {

        }
        getOrderlist()
        onclick()
}


    fun onclick(){
        summeryView.thankObserable().doOnNext { summeryModel.getHomeView() }.subscribe()
    }

    fun getOrderlist(){
        var summerylist=realm.where<OrderModelInfo>().findAll()!!
        print("summerylist===" + summerylist)
        summeryView.showListItems(summerylist)
        deleteCart()
    }

    fun deleteCart(){
                realm.executeTransaction {
            val result=realm.where(CartModelInfo::class.java).findAll()
            result.deleteAllFromRealm()
        }
    }

}