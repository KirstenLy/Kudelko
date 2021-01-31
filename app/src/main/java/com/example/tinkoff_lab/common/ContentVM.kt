package com.example.tinkoff_lab.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tinkoff_lab.data.models.ContentModel
import com.example.tinkoff_lab.data.sources.repository.ContentFilter
import com.example.tinkoff_lab.data.sources.repository.ContentRepository
import com.example.tinkoff_lab.other.ExceptionHandler
import com.example.tinkoff_lab.other.SingleLiveEvent
import com.example.tinkoff_lab.other.plusAssign
import io.reactivex.rxjava3.disposables.CompositeDisposable

/** Common VM for get and store content */
class ContentVM(
    private val contentRepository: ContentRepository,
    private var contentFilter: ContentFilter,
    private val exceptionHandler: ExceptionHandler
) : ViewModel() {

    private val disposables = CompositeDisposable()

    private val _contentSingleItem = MutableLiveData<ContentModel>()
    private val _backAndForwardBtnState = MutableLiveData(BackAndForwardButtonState())
    private val _error = SingleLiveEvent<String>()

    /** @SelfDocumented */
    val contentSingleItemLiveData: LiveData<ContentModel>
        get() = _contentSingleItem

    /** @SelfDocumented */
    val errorEvent: LiveData<String>
        get() = _error

    /** @SelfDocumented */
    val backAndForwardBtnStateEvent: LiveData<BackAndForwardButtonState>
        get() = _backAndForwardBtnState

    init {
        next()
    }

    /**
     * Get next content item
     * */
    fun next() {
        changeFilterByOperation(Operation.GO_FORWARD)
        getContent(Operation.GO_FORWARD)
    }

    /**
     * Get previous content item
     * */
    fun back() {
        changeFilterByOperation(Operation.GO_BACK)
        getContent(Operation.GO_BACK)
    }

    private fun getContent(operation: Operation) {
        disposables += contentRepository
            .read(contentFilter)
            .doOnSubscribe {
                _backAndForwardBtnState.value = BackAndForwardButtonState()
            }
            .doAfterTerminate {
                _backAndForwardBtnState.value = createButtonsStateAfterLoadingEnd()
            }
            .subscribe(
                {
                    _contentSingleItem.value = it
                    _backAndForwardBtnState.value = createButtonsStateAfterLoadingEnd()
                },
                {
                    _error.value = exceptionHandler.getExceptionString(it)
                    rollBackFilterByOperation(operation)
                    _backAndForwardBtnState.value = createButtonsStateAfterLoadingEnd()
                }
            )
    }

    private fun createButtonsStateAfterLoadingEnd() = BackAndForwardButtonState(
        isBackButtonEnabled = !contentFilter.isFirstItem(),
        isForwardButtonEnabled = true
    )

    private fun changeFilterByOperation(operation: Operation) {
        when (operation) {
            Operation.GO_FORWARD -> contentFilter.contentIdx++
            Operation.GO_BACK -> contentFilter.contentIdx--
        }
    }

    private fun rollBackFilterByOperation(operation: Operation) {
        when (operation) {
            Operation.GO_FORWARD -> contentFilter.contentIdx--
            Operation.GO_BACK -> contentFilter.contentIdx++
        }
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }

    private enum class Operation {
        GO_FORWARD,
        GO_BACK
    }
}