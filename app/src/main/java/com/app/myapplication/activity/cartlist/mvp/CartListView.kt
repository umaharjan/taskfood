package com.app.myapplication.activity.cartlist.mvp

import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.myapplication.R
import com.app.myapplication.databasemanager.CartModelInfo
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import io.realm.RealmResults
import kotlinx.android.synthetic.main.cartlist_layout.view.*


class CartListView(private val appCompatActivity: AppCompatActivity) :
    FrameLayout(appCompatActivity) {
    var totalprice:Double=0.0
    var totalDiscount:Double=0.0

    lateinit var cartAdapter: CartAdapter

    init {
        View.inflate(appCompatActivity, R.layout.cartlist_layout, this)

    }


    fun showListItems(arraylist: RealmResults<CartModelInfo>) {
        cartAdapter=CartAdapter(appCompatActivity, arraylist)
        recyclerView.adapter=cartAdapter
        val layoutManager=LinearLayoutManager(appCompatActivity)
        recyclerView.layoutManager=layoutManager

    }

    fun setSummary(result:RealmResults<CartModelInfo>){
        for(i in 0 until result.size){
            totalprice=totalprice!!+result[i]!!.price!!
            totalDiscount=totalDiscount!!+result[i]!!.discount_price!!
        }

        appCompatActivity.runOnUiThread(Runnable {
            tvDiscount.text="Rs. ${totalDiscount}"
            tvSubTotal.text="Rs. ${totalprice}"
            tvShippingCharge.text="Rs 100"
            var totalprice= totalprice+100-totalDiscount
            tvTotal.text="Rs.${totalprice}"
        })
    }


    fun getTotalPrice():String {
        return totalprice.toString()
    }
    fun getTotalDiscount():String{
        return totalDiscount.toString()
    }
    fun getCheckObserable():Observable<Any>{
        return RxView.clicks(btnCheckOut)
    }

    fun getBackObserable():Observable<Any>{
        return  RxView.clicks(ivBack)
    }

    fun getBuyObserable():Observable<Any>{
        return RxView.clicks(BtnBuy)
    }
    fun hideList(){
        cvPriceList.visibility=View.GONE
        btnCheckOut.visibility=View.GONE
        llBuy.visibility=View.VISIBLE
    }
    fun hideBuy(){
        llBuy.visibility=View.GONE
    }

}