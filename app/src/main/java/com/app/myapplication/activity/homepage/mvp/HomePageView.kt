package com.app.myapplication.activity.homepage.mvp

import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.app.myapplication.R
import com.app.myapplication.apputils.SpacesItemDecoration
import com.app.myapplication.databasemanager.foodModelInfo
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import io.realm.RealmResults
import kotlinx.android.synthetic.main.home_list_layout.view.*


class HomePageView(
    private val appCompatActivity: AppCompatActivity,
) : FrameLayout(appCompatActivity) {
    lateinit var homeAdapter:HomeAdapter

    init {
        View.inflate(appCompatActivity, R.layout.home_list_layout, this)

    }


    fun showListItems(arraylist: RealmResults<foodModelInfo>) {
         homeAdapter=HomeAdapter(appCompatActivity, arraylist)
        rvHome.adapter=homeAdapter
        rvHome.layoutManager=GridLayoutManager(appCompatActivity, 2)
        val spacingInPixels=resources.getDimensionPixelSize(R.dimen._1sdp)
        rvHome.addItemDecoration(SpacesItemDecoration(spacingInPixels))


    }
    fun getClickObservable(): Observable<foodModelInfo>? {
        return homeAdapter.getViewClickedObservable()
    }

    fun getCartObserable():Observable<Any>{
        return RxView.clicks(ivCart)
    }

}