package com.app.myapplication.activity.homepage.mvp


import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.app.myapplication.R


class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    internal var tvtitle: TextView = itemView.findViewById(R.id.tvtitle)
    internal var tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
    internal var tvDiscount: TextView = itemView.findViewById(R.id.tvDiscount)
    internal var rlDiscountRibbon: RelativeLayout = itemView.findViewById(R.id.rlDiscountRibbon)
    internal var tvDiscountPercentage: TextView = itemView.findViewById(R.id.tvDiscountPercentage)
    internal var cv_card: CardView= itemView.findViewById(R.id.cv_card)

   internal var ivProduct:ImageView=itemView.findViewById(R.id.ivProduct)
}