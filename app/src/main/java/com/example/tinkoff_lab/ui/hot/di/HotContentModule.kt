package com.example.tinkoff_lab.ui.hot.di

import androidx.lifecycle.ViewModelProvider
import com.example.tinkoff_lab.common.ContentVM
import com.example.tinkoff_lab.other.ViewModelFactory
import com.example.tinkoff_lab.data.sources.repository.ContentFilter
import com.example.tinkoff_lab.data.sources.repository.ContentRepository
import com.example.tinkoff_lab.other.ExceptionHandler
import com.example.tinkoff_lab.ui.hot.HotContentFragment
import dagger.Module
import dagger.Provides

/** Module for HotContent screen */
@Module
object HotContentModule {

    @Provides
    fun provideContentVM(
        fragment: HotContentFragment,
        contentRepository: ContentRepository,
        exceptionHandler: ExceptionHandler
    ) = ViewModelProvider(
        fragment,
        ViewModelFactory { ContentVM(contentRepository, ContentFilter.RandomFilter(), exceptionHandler) })
        .get(ContentVM::class.java)
}