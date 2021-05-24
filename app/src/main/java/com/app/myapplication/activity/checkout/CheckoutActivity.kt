package com.app.myapplication.activity.checkout


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.myapplication.activity.checkout.CheckoutActivity.priceinfo.totalDiscount
import com.app.myapplication.activity.checkout.CheckoutActivity.priceinfo.totalPrice
import com.app.myapplication.activity.checkout.di.CheckoutModule
import com.app.myapplication.activity.checkout.di.DaggerCheckoutComponent
import com.app.myapplication.activity.checkout.mvp.CheckoutPresenter
import com.app.myapplication.activity.checkout.mvp.CheckoutView
import com.app.myapplication.dagger.AppApplication
import javax.inject.Inject


class CheckoutActivity : AppCompatActivity() {

    @Inject
    lateinit var checkoutview: CheckoutView

    @Inject
    lateinit var checkoutPresetner: CheckoutPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appApplication=AppApplication()
        DaggerCheckoutComponent.builder()
            .appComponent(appApplication.get(this).appComponent)
            .checkoutModule(CheckoutModule(this))
            .build()
            .inject(this)
        setContentView(checkoutview)
        checkoutPresetner.onCreateView(totalPrice!!,totalDiscount!!)
    }


    object  priceinfo{
        var totalPrice:String=""
        var totalDiscount:String=""
    }
    companion object {
        fun start(context: Context, price :String, discount:String) {
            totalPrice=price
            totalDiscount=discount
            context.startActivity(Intent(context, CheckoutActivity::class.java))
        }
    }


}