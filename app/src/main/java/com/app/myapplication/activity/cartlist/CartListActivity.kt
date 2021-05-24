package com.app.myapplication.activity.cartlist


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.myapplication.activity.cartlist.di.CartListModule
import com.app.myapplication.activity.cartlist.di.DaggerCartListComponent
import com.app.myapplication.activity.cartlist.mvp.CartListPresenter
import com.app.myapplication.activity.cartlist.mvp.CartListView
import com.app.myapplication.dagger.AppApplication
import javax.inject.Inject


class CartListActivity : AppCompatActivity() {

    @Inject
    lateinit var cartListView: CartListView

    @Inject
    lateinit var cartListPresenter: CartListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appApplication=AppApplication()
        DaggerCartListComponent.builder()
            .appComponent(appApplication.get(this).appComponent)
            .cartListModule(CartListModule(this))
            .build()
            .inject(this)
        setContentView(cartListView)
        cartListPresenter.onCreateView()
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, CartListActivity::class.java))
        }
    }


}