package com.example.tinkoff_lab.data.sources.repository

import com.example.tinkoff_lab.application.di.ApplicationScope
import com.example.tinkoff_lab.data.models.ContentModel
import com.example.tinkoff_lab.data.sources.network.NetworkApi
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

/** Sync UI and cache indexes: UI range is [1..inf], cache is [0..inf] */
private const val DIFF_FOR_CACHE_IDX = 1

/** Repository for load and cache content */
// TODO: Вынести определение места подписки и свитч тредов в какой - нибудь интерактор или сущность, которая будет за это ответственна
@ApplicationScope
class ContentRepository @Inject constructor(private val networkApi: NetworkApi) {

    private val randomContentCache = mutableListOf<ContentModel>()

    /**
     * Get content by filter.
     * When take content from cache, fix index from filter, @see [DIFF_FOR_CACHE_IDX]
     * */
    fun read(contentFilter: ContentFilter): Single<ContentModel> {
        val contentIdx = contentFilter.contentIdx
        return when (contentFilter) {
            is ContentFilter.RandomFilter -> {
                if (isAvailableReturnRandomContentFromCache(contentIdx)) {
                    val fixedIdx = fixContentIdxForTakeFromCache(contentIdx)
                    return Single.just(randomContentCache[fixedIdx])
                }
                networkApi
                    .getRandomContentItem()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doAfterSuccess(randomContentCache::add)
            }
            else                          -> throw UnsupportedOperationException("Unknown content filter")
        }
    }

    private fun isAvailableReturnRandomContentFromCache(contentIdx: Int) = contentIdx <= randomContentCache.size

    private fun fixContentIdxForTakeFromCache(contentIdx: Int) = contentIdx - DIFF_FOR_CACHE_IDX
}