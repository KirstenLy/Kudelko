package com.example.tinkoff_lab.data.sources.repository

import com.example.tinkoff_lab.application.di.ApplicationScope
import com.example.tinkoff_lab.data.models.ContentModel
import com.example.tinkoff_lab.data.sources.network.NetworkApi
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

/** Repository for load and cache content */
// TODO: Вынести определение места подписки и свитч тредов в какой - нибудь интерактор или сущность, которая будет за это ответственна
@ApplicationScope
class ContentRepository @Inject constructor(private val networkApi: NetworkApi) {

    private val randomContentCache = mutableListOf<ContentModel>()

    /** Get content by filter */
    fun read(contentFilter: ContentFilter): Single<ContentModel> {
        return when (contentFilter) {
            is ContentFilter.RandomFilter -> {
                if (isAvailableReturnRandomContentFromCache(contentFilter.contentIdx)) {
                    return Single.just(randomContentCache[contentFilter.contentIdx])
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

    private fun isAvailableReturnRandomContentFromCache(contentIdx: Int) = contentIdx  <= randomContentCache.size - 1
}