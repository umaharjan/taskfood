package com.app.myapplication.activity.fooddetail.mvp

import androidx.appcompat.app.AppCompatActivity
import com.app.myapplication.databasemanager.CartModelInfo
import com.app.myapplication.databasemanager.foodModelInfo
import io.realm.Realm
import io.realm.RealmResults
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import java.util.*


class FoodDetailPresenter(
    private val foodDetailView: FoodDetailView,
    private val foodDetailModel: FoodDetailModel,
    private val appCompatActivity: AppCompatActivity
) {

    private lateinit var realm: Realm
    var Id: String=""
    lateinit var foodinfo: foodModelInfo

    fun onCreateView(info: foodModelInfo) {
        foodDetailView.getInfo(info)
        foodinfo=info
        Id=info.id
        onclick()
    }

    fun onclick(){
        foodDetailView.cartObserable().doOnNext{ isalreadyExist() }.subscribe()
        foodDetailView.getBackObserable().doOnNext { foodDetailModel.getFinished() }.subscribe()
    }


    fun getInsertIntoCart(info: foodModelInfo) {
        val myRealm=Realm.getDefaultInstance()
        myRealm.executeTransaction { realm ->
            // Add a person
            val foodinfo=realm.createObject<CartModelInfo>(UUID.randomUUID().toString())
            foodinfo.foodId=info.id
            foodinfo.name=info.name
            foodinfo.image=info.image
            foodinfo.price=info.price
            foodinfo.discount=info.discount
            foodinfo.discount_price=info.discount_price
            foodinfo.total=1
            foodinfo.decription=info.decription
            foodinfo.created_on=System.currentTimeMillis()
        }
        Thread.sleep(1000)
        foodDetailModel.getCartlistView()
    }

    fun isalreadyExist() {

        realm=Realm.getDefaultInstance()
        try {
            val cartlist=realm.where<CartModelInfo>().equalTo("foodId", Id).findAll()!!
            when {
                cartlist.size <= 0 -> {

                    getInsertIntoCart(foodinfo)
                }
                else -> {
                    var carttotal=realm.where<CartModelInfo>().equalTo("foodId", Id).findFirst()!!
                    var usertotal=carttotal.total!! + 1
                    var totalprice=carttotal.price!!*usertotal
                    var totalDiscountPrice=carttotal.discount_price!!*usertotal
                    delete()
                    Thread.sleep(1000)
                    getInserUpdatetIntoCart(foodinfo, usertotal,totalprice,totalDiscountPrice)
                }
            }
        } catch (ex: Exception) {

        }
    }


    fun delete() {
        realm.executeTransaction {
            val result: RealmResults<CartModelInfo> = realm.where(CartModelInfo::class.java).equalTo("foodId", Id).findAll()
            result.deleteAllFromRealm()

        }
    }


    fun getInserUpdatetIntoCart(info: foodModelInfo, total: Int, totalprice :Double, totaldiscountPrice:Double) {
        val myRealm=Realm.getDefaultInstance()
        myRealm.executeTransaction { realm ->
            // Add a person
            val foodinfo=realm.createObject<CartModelInfo>(UUID.randomUUID().toString())
            foodinfo.foodId=info.id
            foodinfo.name=info.name
            foodinfo.image=info.image
            foodinfo.price=totalprice
            foodinfo.discount=info.discount
            foodinfo.discount_price=totaldiscountPrice
            foodinfo.total=total
            foodinfo.decription=info.decription
            foodinfo.created_on=System.currentTimeMillis()
        }
        val cartlist=realm.where<CartModelInfo>().findAll()!!
        Thread.sleep(1000)
        foodDetailModel.getCartlistView()
        println("cart list==="+cartlist)
    }


}