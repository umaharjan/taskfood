package com.app.myapplication.activity.summery.mvp


import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.myapplication.R


class SummaryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    internal var tvName: TextView=itemView.findViewById(R.id.tvName)
    internal var tvCount: TextView=itemView.findViewById(R.id.tvCount)

    internal var tvTotal: TextView=itemView.findViewById(R.id.tvTotal)
}