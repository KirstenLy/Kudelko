package com.example.tinkoff_lab.application.di

import android.content.Context
import com.example.tinkoff_lab.BuildConfig
import com.example.tinkoff_lab.application.App
import com.example.tinkoff_lab.common.ResourceProvider
import com.example.tinkoff_lab.data.sources.network.NetworkApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/** Module to provide dependencies with app scope: retrofit, okhttp, persistence db, pref etc */
@Module
object AppModule {

    /** Provide api for network requests */
    @ApplicationScope
    @Provides
    fun provideApi(): NetworkApi = createRetrofit().create(NetworkApi::class.java)

    /** Provide resource provider for VM, repositories, e.t.c */
    @ApplicationScope
    @Provides
    fun provideResourceProvider(appContext: Context) = ResourceProvider(appContext)

    /** Provide Application context */
    @ApplicationScope
    @Provides
    fun provideContext(app: App): Context = app.applicationContext

    private fun createRetrofit() = Retrofit.Builder()
        .baseUrl(BuildConfig.SERVER_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}