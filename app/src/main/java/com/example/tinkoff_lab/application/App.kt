package com.example.tinkoff_lab.application

import com.example.tinkoff_lab.application.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

/**@SelfDocumented*/
class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<App> {
        appLevelInjector = DaggerAppComponent.factory().create(this) as DaggerAppComponent
        return DaggerAppComponent.factory().create(this)
    }

    /** Need for provide dependencies into views or simple classes */
    companion object {
        lateinit var appLevelInjector: DaggerAppComponent
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}