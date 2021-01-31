package com.example.tinkoff_lab.data.sources.repository

sealed class ContentFilter(var contentIdx: Int) {
    class RandomFilter(contentIdx: Int = 0) : ContentFilter(contentIdx)
    class HotFilter(contentIdx: Int = 0) : ContentFilter(contentIdx)

    /** @SelfDocumented */
    fun isFirstItem() = contentIdx == 0 || contentIdx == 1
}