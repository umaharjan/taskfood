package com.app.myapplication.activity.summery.mvp


import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.myapplication.R
import com.app.myapplication.activity.cartlist.mvp.CartViewHolder
import com.app.myapplication.databasemanager.CartModelInfo
import com.app.myapplication.databasemanager.OrderModelInfo
import com.squareup.picasso.Picasso
import io.realm.RealmResults


class SummaryAdapter(
    private val mcontext: Context,
    private val requestList: RealmResults<OrderModelInfo>
) : RecyclerView.Adapter<SummaryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SummaryViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_summary, parent, false)
        val viewHolder=SummaryViewHolder(view)
        return viewHolder
    }

    override fun onBindViewHolder(holder: SummaryViewHolder, position: Int) {
        val quickModel=requestList[position]
        holder.tvName.setText("${quickModel!!.name}")
        holder.tvCount.text="${quickModel!!.total}"

        holder.tvTotal.text="Rs. ${quickModel!!.price}"
    }


    override fun getItemCount(): Int {
        return requestList.size
    }


}