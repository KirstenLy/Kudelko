package com.example.tinkoff_lab.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tinkoff_lab.data.models.ContentModel
import com.example.tinkoff_lab.data.sources.repository.ContentFilter
import com.example.tinkoff_lab.data.sources.repository.ContentRepository
import com.example.tinkoff_lab.other.plusAssign
import io.reactivex.rxjava3.disposables.CompositeDisposable
import timber.log.Timber

/** Common VM for get and store content */
class ContentVM(
    private val contentRepository: ContentRepository,
    private var contentFilter: ContentFilter
) : ViewModel() {

    private val disposables = CompositeDisposable()

    // TODO: На данный момент поддержан только случайный контент, лист заведён на будущее
    private val _contentSingleItem = MutableLiveData<List<ContentModel>>()

    /** @SelfDocumented */
    val contentSingleItemLiveData: LiveData<List<ContentModel>>
        get() = _contentSingleItem

    init {
        list()
    }

    /**
     * Get content using filter.
     * */
    fun list() {
        disposables += contentRepository
            .read(contentFilter)
            .subscribe({ _contentSingleItem.value = listOf(it) }, Timber::e)
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}