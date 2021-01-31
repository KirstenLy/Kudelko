package com.example.tinkoff_lab.other

import com.google.gson.annotations.SerializedName

class BaseResponse<T>(@SerializedName("result") val data: T?)