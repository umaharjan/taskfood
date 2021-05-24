package com.app.myapplication.activity.cartlist.mvp


import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.myapplication.R
import com.app.myapplication.databasemanager.CartModelInfo
import com.squareup.picasso.Picasso
import io.realm.RealmResults


class CartAdapter(
    private val mcontext: Context,
    private val requestList: RealmResults<CartModelInfo>
) : RecyclerView.Adapter<CartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        val viewHolder=CartViewHolder(view)
        return viewHolder
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val quickModel=requestList[position]
        holder.etCount.setText("${quickModel!!.total}")
        holder.tvTitle.text=quickModel.name
        when {
            quickModel.discount == 0.0 -> {
                holder.rlDiscountRibbon.visibility=View.GONE
                holder.tvDiscount.visibility=View.GONE
                holder.tvPrice.text="${quickModel.price}"

            }
            else -> {
                var price=quickModel!!.price!! - quickModel!!.discount_price!!
                holder.rlDiscountRibbon.visibility=View.VISIBLE
                holder.tvDiscountPercentage.text="${quickModel.discount}%"
                holder.tvDiscount.text="${quickModel.price}"
                holder.tvDiscount.paintFlags=
                    holder.tvPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                holder.tvDiscount.visibility=View.VISIBLE
                holder.tvPrice.text="${price}"
            }
        }
        try {
            Picasso.with(mcontext).load(quickModel!!.image!!).into(holder.ivCartImage)
        } catch (ex: Exception) {


        }
    }


    override fun getItemCount(): Int {
        return requestList.size
    }


}