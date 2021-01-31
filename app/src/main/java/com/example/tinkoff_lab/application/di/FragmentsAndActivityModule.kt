package com.example.tinkoff_lab.application.di

import com.example.tinkoff_lab.ui.hot.HotContentFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsAndActivityModule {

//    @ContributesAndroidInjector(modules = [MainActivityModule::class])
//    abstract fun contributeActivityAndroidInjector(): MainActivity
//
    @ContributesAndroidInjector
    abstract fun contributeHotContentFragmentAndroidInjector(): HotContentFragment

}