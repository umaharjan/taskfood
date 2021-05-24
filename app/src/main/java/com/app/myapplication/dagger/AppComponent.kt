package com.app.myapplication.dagger

import android.content.Context

import dagger.Component
import dagger.android.AndroidInjectionModule


@AppScope
@Component(modules=arrayOf(AndroidInjectionModule::class, AppModule::class))
interface AppComponent {
    fun context(): Context


}
