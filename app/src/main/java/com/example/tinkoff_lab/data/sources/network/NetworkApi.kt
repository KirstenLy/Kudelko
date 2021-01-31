package com.example.tinkoff_lab.data.sources.network

import com.example.tinkoff_lab.data.models.ContentModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

/** API for network requests */
interface NetworkApi {

    @GET("random?json=true")
    fun getRandomContentItem(): Single<ContentModel>
}