package com.app.myapplication.activity.homepage.mvp

import androidx.appcompat.app.AppCompatActivity
import com.app.myapplication.R
import com.app.myapplication.activity.homepage.dto.PullHomeModel
import com.app.myapplication.databasemanager.foodModelInfo
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.realm.Realm
import io.realm.RealmResults
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import java.util.*
import kotlin.collections.ArrayList

class HomePagePresenter(
    private val homePageView: HomePageView,
    private val homePageModel: HomePageModel,
    private val appCompatActivity: AppCompatActivity
) {
    private var datalist=ArrayList<PullHomeModel>()
    private lateinit var realm: Realm
    private val compositeDisposable=CompositeDisposable()
    fun onCreateView() {
        realm=Realm.getDefaultInstance()
        try {
            val foodlist=realm.where<foodModelInfo>().findAll()!!
            when {
                foodlist.size <= 0 -> {
                    menuDataList()
                }
                else -> {
                    homePageView.showListItems(getContacts())
                }
            }
        } catch (ex: Exception) {

        }

        onItemClick()
        onclick()

    }

    fun onclick(){
        homePageView.getCartObserable().doOnNext{ homePageModel.getCartView() }.subscribe()
    }

    private fun menuDataList() {
        datalist=ArrayList()
        datalist.add(
            PullHomeModel(
                "Momo",
                UUID.randomUUID().toString(),
                R.drawable.ic_momo,
                140.0,
                15.0,
                20.0,
                100,
                appCompatActivity.getString(R.string.description),
                System.currentTimeMillis()

            )
        )
        datalist.add(
            PullHomeModel(
                "Pizza",
                UUID.randomUUID().toString(),
                R.drawable.ic_pizza,
                300.0,
                20.0,
                40.0,
                100,
                appCompatActivity.getString(R.string.description),
                System.currentTimeMillis()

            )
        )
        datalist.add(
            PullHomeModel(
                "Burger",
                UUID.randomUUID().toString(),
                R.drawable.ic_burger,
                100.0,
                10.0,
                10.0,
                100,
                appCompatActivity.getString(R.string.description),
                System.currentTimeMillis()

            )
        )
        datalist.add(
            PullHomeModel(
                "Chowmein",
                UUID.randomUUID().toString(),
                R.drawable.ic_chowmein,
                150.0,
                10.0,
                15.0,
                100,
                appCompatActivity.getString(R.string.description),
                System.currentTimeMillis()

            )
        )
        datalist.add(
            PullHomeModel(
                "Coke",
                UUID.randomUUID().toString(),
                R.drawable.ic_coke,
                140.0,
                15.0,
                20.0,
                100,
                appCompatActivity.getString(R.string.description),
                System.currentTimeMillis()

            )
        )
        datalist.add(
            PullHomeModel(
                "Chicken Piece",
                UUID.randomUUID().toString(),
                R.drawable.ic_cicken,
                400.0,
                25.0,
                40.0,
                100,
                appCompatActivity.getString(R.string.description),
                System.currentTimeMillis()

            )
        )
        datalist.add(
            PullHomeModel(
                "Sausuge",
                UUID.randomUUID().toString(),
                R.drawable.ic_sausage,
                30.0,
                0.0,
                0.0,
                100,
                appCompatActivity.getString(R.string.description),
                System.currentTimeMillis()

            )
        )
        datalist.add(
            PullHomeModel(
                "Sandwich",
                UUID.randomUUID().toString(),
                R.drawable.ic_sandwich,
                70.0,
                0.0,
                0.0,
                100,
                appCompatActivity.getString(R.string.description),
                System.currentTimeMillis()

            )
        )

        insertContactDb()

    }

    fun insertContactDb() {
        for (i in 0 until datalist.size) {
            val myRealm=Realm.getDefaultInstance()
            myRealm.executeTransaction { realm ->
                // Add a person
                val foodinfo=realm.createObject<foodModelInfo>(datalist[i].getId())
                foodinfo.name=(datalist[i].getName())
                foodinfo.image=(datalist[i].getImage())
                foodinfo.price=(datalist[i].getPrice())
                foodinfo.discount=(datalist[i].getDiscount())
                foodinfo.discount_price=(datalist[i].getDiscountPrice())
                foodinfo.total=(datalist[i].getTotal())
                foodinfo.decription=(datalist[i].getDescription())
                foodinfo.created_on=(datalist[i].getCreatedOn())
            }



        }
        homePageView.showListItems(getContacts())
    }

    fun getContacts(): RealmResults<foodModelInfo> {
        return realm.where(foodModelInfo::class.java).findAll()
    }


    private fun onItemClick() {
        val disposableObserver=getItemClickObserver()
        homePageView.getClickObservable()!!.subscribe(disposableObserver)
        compositeDisposable.add(disposableObserver)
    }

    private fun getItemClickObserver(): DisposableObserver<foodModelInfo> {
        return object : DisposableObserver<foodModelInfo>() {
            override fun onNext(pullModel: foodModelInfo) {
                println("foood name===" + pullModel.name)
                homePageModel.getFoodDetail(pullModel)
            }

            override fun onError(e: Throwable) {
            }

            override fun onComplete() {
            }
        }
    }
}