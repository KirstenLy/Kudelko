package com.example.tinkoff_lab.data.sources.repository

sealed class ContentFilter {
    object RandomFilter : ContentFilter()
    object HotFilter : ContentFilter()
}