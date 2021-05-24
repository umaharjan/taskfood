package com.app.myapplication.activity.summery.di

import androidx.appcompat.app.AppCompatActivity
import com.app.myapplication.activity.summery.mvp.SummeryModel
import com.app.myapplication.activity.summery.mvp.SummeryPresenter
import com.app.myapplication.activity.summery.mvp.SummeryView
import com.app.myapplication.dagger.AppActivity
import dagger.Module
import dagger.Provides

@Module
class SummeryModule(private val appCompatActivity: AppCompatActivity) {
    @AppActivity
    @Provides
    fun summaryView(): SummeryView {
        return SummeryView(appCompatActivity)
    }

    @AppActivity
    @Provides
    fun summaryPresenter(summaryview: SummeryView, summarymodel: SummeryModel): SummeryPresenter {
        return SummeryPresenter(summaryview, summarymodel, appCompatActivity)
    }

    @AppActivity
    @Provides
    fun summaryModel(): SummeryModel {
        return SummeryModel(appCompatActivity)
    }
}