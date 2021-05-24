package com.app.myapplication.activity.summery.mvp

import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.myapplication.R
import com.app.myapplication.databasemanager.OrderModelInfo
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import io.realm.RealmResults
import kotlinx.android.synthetic.main.summery_layout.view.*


class SummeryView(private val appCompatActivity: AppCompatActivity) :
    FrameLayout(appCompatActivity) {
    lateinit var summaryAdapter: SummaryAdapter

    init {
        View.inflate(appCompatActivity, R.layout.summery_layout, this)

    }

    fun showListItems(arraylist: RealmResults<OrderModelInfo>) {

        summaryAdapter=SummaryAdapter(appCompatActivity, arraylist)
        recyclerView.adapter=summaryAdapter
        val layoutManager=LinearLayoutManager(appCompatActivity)
        recyclerView.layoutManager=layoutManager

    }

    fun setSummary(price: Double, discount: Double) {
        appCompatActivity.runOnUiThread(Runnable {
            tvDiscount.text="Rs. ${discount}"
            tvSubTotal.text="Rs. ${price}"
            tvShippingCharge.text="Rs 100"
            var totalprice=price + 100 - discount
            tvTotal.text="Rs.${totalprice}"
        })
    }

    fun thankObserable():Observable<Any>{
        return RxView.clicks(btnThank)
    }


}