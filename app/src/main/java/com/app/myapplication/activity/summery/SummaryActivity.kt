package com.app.myapplication.activity.summery


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.myapplication.activity.cartlist.di.CartListModule
import com.app.myapplication.activity.cartlist.di.DaggerCartListComponent
import com.app.myapplication.activity.cartlist.mvp.CartListPresenter
import com.app.myapplication.activity.cartlist.mvp.CartListView
import com.app.myapplication.activity.summery.SummaryActivity.priceinfo.totalDiscount
import com.app.myapplication.activity.summery.SummaryActivity.priceinfo.totalPrice
import com.app.myapplication.activity.summery.di.DaggerSummeryComponent
import com.app.myapplication.activity.summery.di.SummeryModule
import com.app.myapplication.activity.summery.mvp.SummeryPresenter
import com.app.myapplication.activity.summery.mvp.SummeryView
import com.app.myapplication.dagger.AppApplication
import javax.inject.Inject


class SummaryActivity : AppCompatActivity() {

    @Inject
    lateinit var summeryView: SummeryView

    @Inject
    lateinit var summeryPresenter: SummeryPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appApplication=AppApplication()
        DaggerSummeryComponent.builder()
            .appComponent(appApplication.get(this).appComponent)
            .summeryModule(SummeryModule(this))
            .build()
            .inject(this)
        setContentView(summeryView)
        summeryPresenter.onCreateView(totalPrice!!,totalDiscount!!)
    }

    companion object {
        fun start(context: Context,price :String, discount:String) {
            totalPrice=price
            totalDiscount=discount
            context.startActivity(Intent(context, SummaryActivity::class.java))
        }
    }

    object  priceinfo{
        var totalPrice:String=""
        var totalDiscount:String=""
    }


}