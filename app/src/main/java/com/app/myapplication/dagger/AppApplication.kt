package com.app.myapplication.dagger


import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.app.myapplication.BuildConfig

import io.realm.Realm
import io.realm.RealmConfiguration


class AppApplication : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent=DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
        initializeRealm()
    }

    fun get(activity: Activity): AppApplication {
        return activity.application as AppApplication
    }

    fun appComponent(): AppComponent? {
        return appComponent
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    private fun initializeRealm() {
        Realm.init(this)
        val config=RealmConfiguration.Builder()
            .allowQueriesOnUiThread(true)
            .allowWritesOnUiThread(true)
            .deleteRealmIfMigrationNeeded()
            .schemaVersion(2) // Must be bumped when the schema changes
            .build()
        Realm.setDefaultConfiguration(config)
    }

}