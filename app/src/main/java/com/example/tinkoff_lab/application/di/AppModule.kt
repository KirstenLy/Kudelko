package com.example.tinkoff_lab.application.di

import android.content.Context
import com.example.tinkoff_lab.BuildConfig
import com.example.tinkoff_lab.application.App
import com.example.tinkoff_lab.data.sources.network.NetworkApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/** Module to provide dependencies with app scope: retrofit, okhttp, persistence db, pref etc */
@Module
object AppModule {

    /** Provide api for network requests */
    @ApplicationScope
    @Provides
    fun provideApi(): NetworkApi = createRetrofit().create(NetworkApi::class.java)

    /** Provide Application context */
    @ApplicationScope
    @Provides
    fun provideContext(app: App): Context = app.applicationContext

    private fun createRetrofit() = Retrofit.Builder()
        .baseUrl(BuildConfig.SERVER_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
}