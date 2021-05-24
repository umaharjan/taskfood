package com.app.myapplication.activity.cartlist.mvp


import android.view.View
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.app.myapplication.R


class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {



    internal var etCount: EditText=itemView.findViewById(R.id.etCount)
    internal  var  ivCartImage:ImageView=itemView.findViewById(R.id.ivCartImage)
    internal  var ivDelete:ImageView=itemView.findViewById(R.id.ivDelete)

    internal  var  tvBrand:TextView=itemView.findViewById(R.id.tvBrand)
    internal  var  tvTitle:TextView=itemView.findViewById(R.id.tvTitle)
    internal  var  tvPrice:TextView=itemView.findViewById(R.id.tvPrice)

    internal var tvDiscount: TextView = itemView.findViewById(R.id.tvDiscount)
    internal var rlDiscountRibbon: RelativeLayout = itemView.findViewById(R.id.rlDiscountRibbon)
    internal var tvDiscountPercentage: TextView = itemView.findViewById(R.id.tvDiscountPercentage)

}