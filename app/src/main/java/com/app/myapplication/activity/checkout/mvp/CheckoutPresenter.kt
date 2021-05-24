package com.app.myapplication.activity.checkout.mvp

import androidx.appcompat.app.AppCompatActivity
import com.app.myapplication.databasemanager.CartModelInfo
import com.app.myapplication.databasemanager.OrderModelInfo
import io.realm.Realm
import io.realm.RealmResults
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import java.util.*

class CheckoutPresenter(
    private val checkoutView: CheckoutView,
    private val checkoutModel: CheckoutModel,
    private val appCompatActivity: AppCompatActivity
) {
    private lateinit var realm: Realm
    lateinit var cartlist: RealmResults<CartModelInfo>
    var totalprice: String=""
    var totalDiscount: String=""
    fun onCreateView(price: String, discount: String) {
        try {
            totalprice=price
            totalDiscount=discount

            checkoutView.setSummary(price.toDouble(), discount.toDouble())
        } catch (ex: Exception) {

        }
        realm=Realm.getDefaultInstance()
        cartlist=realm.where<CartModelInfo>().findAll()!!
        println("cartlist===" + cartlist.size)
        when {
            cartlist.size > 0 -> {
            }
            else -> {
            }
        }
        onClick()
    }

    fun onClick() {
        checkoutView.getCheckObserable().doOnNext {
            checkoutView.showProgressDialog()
            deleteOrderList()
            insertContactDb()
        }.subscribe()

        checkoutView.getBackObserable().doOnNext { checkoutModel.getFinished() }.subscribe()
    }

    fun insertContactDb() {
        for (i in 0 until cartlist.size) {
            val myRealm=Realm.getDefaultInstance()
            myRealm.executeTransaction { realm ->
                val foodinfo=realm.createObject<OrderModelInfo>(UUID.randomUUID().toString())
                foodinfo.name=(cartlist[i]!!.name)
                foodinfo.image=(cartlist[i]!!.image)
                foodinfo.price=(cartlist[i]!!.price)
                foodinfo.discount=(cartlist[i]!!.discount)
                foodinfo.discount_price=(cartlist[i]!!.discount_price)
                foodinfo.total=(cartlist[i]!!.total)
                foodinfo.decription=(cartlist[i]!!.decription)
                foodinfo.created_on=System.currentTimeMillis()
            }
        }
        Thread.sleep(2000)
        delete()
    }

    fun delete() {
        checkoutView.hidProgressDialog()
        checkoutModel.getSummeryView(totalprice, totalDiscount)

        //  checkoutModel.getSummeryView()
    }

    fun deleteOrderList() {
        realm.executeTransaction {
            val result=realm.where(OrderModelInfo::class.java).findAll()
            result.deleteAllFromRealm()
        }
    }
}