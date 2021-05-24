package com.app.myapplication.activity.fooddetail.di

import androidx.appcompat.app.AppCompatActivity
import com.app.myapplication.activity.fooddetail.mvp.FoodDetailModel
import com.app.myapplication.activity.fooddetail.mvp.FoodDetailPresenter
import com.app.myapplication.activity.fooddetail.mvp.FoodDetailView
import com.app.myapplication.dagger.AppActivity
import dagger.Module
import dagger.Provides


@Module
class FoodDetailModule(private val appCompatActivity: AppCompatActivity) {

    @AppActivity
    @Provides
    fun detailView(): FoodDetailView {
        return FoodDetailView(appCompatActivity)
    }

    @AppActivity
    @Provides
    fun detailPresenter(detailView: FoodDetailView, detailModel: FoodDetailModel):FoodDetailPresenter {
        return FoodDetailPresenter(detailView, detailModel,appCompatActivity)
    }

    @AppActivity
    @Provides
    fun detailModel(): FoodDetailModel {
        return FoodDetailModel(appCompatActivity)
    }

}