package com.example.tinkoff_lab.data.models

import com.google.gson.annotations.SerializedName

/** Model for content entity */
class ContentModel(
    @SerializedName("id") val id: Int,
    @SerializedName("description") val description: String,
    @SerializedName("gifURL") val gifURL: String
)