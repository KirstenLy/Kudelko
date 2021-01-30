package com.example.tinkoff_lab.data.sources.network

import com.example.tinkoff_lab.common.BaseResponse
import retrofit2.http.*

/** API for network requests */
interface NetworkApi {

    @GET("advice/get_all.php")
    suspend fun getAllAdvices(): BaseResponse<Unit>
}