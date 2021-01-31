package com.example.tinkoff_lab.application.di

import com.example.tinkoff_lab.application.App
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

/** Component, provide app scoped dependencies */
@ApplicationScope
@Component(modules = [AndroidInjectionModule::class, AppModule::class, FragmentsAndActivityModule::class])
interface AppComponent : AndroidInjector<App> {

    @Component.Factory
    interface Factory: AndroidInjector.Factory<App>
}