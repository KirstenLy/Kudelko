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

    // TODO: Подумать, нужно ли очищать хранилище подписок при next/back операциях, чтобы защититься от фаст клика. Или мб локать кнопки на UI.
    private val disposables = CompositeDisposable()

    private val _contentSingleItem = MutableLiveData<ContentModel>()
    private val _isBackButtonVisible = MutableLiveData<Boolean>()

    /** @SelfDocumented */
    val contentSingleItemLiveData: LiveData<ContentModel>
        get() = _contentSingleItem

    /** @SelfDocumented */
    val isBackButtonVisibleLiveData: LiveData<Boolean>
        get() = _isBackButtonVisible

    init {
        changeBackButtonVisibilityIfNeeded()
        getContent()
    }

    /**
     * Get next content item
     * */
    fun next() {
        contentFilter.contentIdx++
        changeBackButtonVisibilityIfNeeded()
        getContent()
    }

    /**
     * Get previous content item
     * */
    fun back() {
        contentFilter.contentIdx--
        changeBackButtonVisibilityIfNeeded()
        getContent()
    }

    private fun getContent() {
        disposables += contentRepository
            .read(contentFilter)
            .subscribe(_contentSingleItem::setValue, Timber::e)
    }

    private fun changeBackButtonVisibilityIfNeeded() {
        _isBackButtonVisible.value = !contentFilter.isFirstItem()
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}