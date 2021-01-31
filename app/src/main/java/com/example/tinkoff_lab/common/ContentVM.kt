package com.example.tinkoff_lab.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tinkoff_lab.data.models.ContentModel
import com.example.tinkoff_lab.data.sources.repository.ContentFilter
import com.example.tinkoff_lab.data.sources.repository.ContentRepository
import com.example.tinkoff_lab.other.plusAssign
import io.reactivex.rxjava3.disposables.CompositeDisposable

/** VM with advices data operations */
class ContentVM(
    private val contentRepository: ContentRepository,
    private var contentFilter: ContentFilter
) : ViewModel() {

    private val disposables = CompositeDisposable()

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
            .subscribe()
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}