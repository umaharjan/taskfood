package com.app.myapplication.apputils

import android.graphics.Rect

import android.view.View
import androidx.recyclerview.widget.RecyclerView


class SpacesItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View,
                                parent: RecyclerView, state: RecyclerView.State) {
        outRect.left=space
        outRect.right=space


        // Add top margin only for the first item to avoid double space between items
        if (parent.getChildLayoutPosition(view) == 0) {

        } else {

        }
    }
}