package com.example.tinkoff_lab.common

import com.google.gson.annotations.SerializedName

class BaseResponse<T>(@SerializedName("data") val data: T?)