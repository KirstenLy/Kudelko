package com.example.tinkoff_lab.application.di

import com.example.tinkoff_lab.ui.hot.HotContentFragment
import com.example.tinkoff_lab.ui.hot.di.HotContentModule
import com.example.tinkoff_lab.ui.random.RandomContentFragment
import com.example.tinkoff_lab.ui.random.di.RandomContentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsAndActivityModule {

    @ContributesAndroidInjector(modules = [RandomContentModule::class])
    abstract fun contributeRandomContentFragmentAndroidInjector(): RandomContentFragment

    @ContributesAndroidInjector(modules = [HotContentModule::class])
    abstract fun contributeHotContentFragmentAndroidInjector(): HotContentFragment
}