package com.app.myapplication.activity.summery.mvp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.app.myapplication.activity.homepage.HomePageActivity

class SummeryModel(private val appCompatActivity: AppCompatActivity) {

    fun getHomeView() {
        val intent = Intent(appCompatActivity, HomePageActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        intent.putExtra("EXIT", true)
        appCompatActivity.startActivity(intent)
        appCompatActivity.finish()
    }
}