package com.app.myapplication.activity.homepage.mvp


import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.myapplication.R
import com.app.myapplication.databasemanager.foodModelInfo
import com.jakewharton.rxbinding2.view.RxView
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import io.realm.RealmResults


class HomeAdapter(
    private val mcontext: Context,
    private val requestList: RealmResults<foodModelInfo>
) : RecyclerView.Adapter<HomeViewHolder>() {


    private val clickSubject=PublishSubject.create<foodModelInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view=
            LayoutInflater.from(parent.context).inflate(R.layout.item_home_layout, parent, false)
        val viewHolder=HomeViewHolder(view)

        RxView.clicks(view)
            .takeUntil(RxView.detaches(parent))
            .map { requestList!![viewHolder.adapterPosition] }
            .subscribe(clickSubject)
        return viewHolder
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val quickModel=requestList!![position]
        holder.tvtitle.text=quickModel!!.name
        holder.tvPrice.text="${quickModel!!.price}"
        try {
            Picasso.with(mcontext).load(quickModel.image!!).into(holder.ivProduct)
        } catch (ex: Exception) {

        }
        when {
            quickModel.discount == 0.0 -> {
                holder.rlDiscountRibbon.visibility=View.GONE

                holder.tvPrice.text="Rs. ${quickModel.price}"
            }
            else -> {
                holder.rlDiscountRibbon.visibility=View.VISIBLE
                try {
                    holder.tvDiscountPercentage.text=
                        (quickModel.discount!!.toInt()).toString() + "%"
                } catch (ex: Exception) {

                }
                var price=quickModel!!.price!! - quickModel!!.discount_price!!
                holder.tvDiscount.text="Rs. ${quickModel.price}"
                holder.tvDiscount.paintFlags=
                    holder.tvDiscount.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                holder.tvPrice.text="Rs. ${price!!}"
            }
        }
    }


    override fun getItemCount(): Int {
        return requestList!!.size
    }

    fun getViewClickedObservable(): Observable<foodModelInfo>? {
        return clickSubject
    }

}