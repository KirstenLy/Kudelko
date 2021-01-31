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

    /** Get content by filter */
    // TODO: Потенциально, read должен поддерживать эндпоинты, которые возвращают не только один итем, но и список итемов.
    // TODO: Когда будет начата поддержка категорий "горячее/последнее", нужно заняться определнием того что read возвращает
    fun read(contentFilter: ContentFilter): Single<ContentModel> {
        return when (contentFilter) {
            is ContentFilter.RandomFilter -> {
                networkApi
                    .getRandomContentItem()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
            else                          -> throw UnsupportedOperationException("Unknown content filter")
        }
    }
}