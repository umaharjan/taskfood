package com.app.myapplication.activity.summery.di

import com.app.myapplication.activity.summery.SummaryActivity
import com.app.myapplication.dagger.AppActivity
import com.app.myapplication.dagger.AppComponent
import dagger.Component


@AppActivity
@Component(modules=[(SummeryModule::class)], dependencies=[(AppComponent::class)])
interface SummeryComponent {
    abstract fun inject(summaryActivity: SummaryActivity)
}