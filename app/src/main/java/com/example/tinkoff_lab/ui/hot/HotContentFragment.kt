package com.example.tinkoff_lab.ui.hot

import com.example.tinkoff_lab.R
import com.example.tinkoff_lab.data.sources.repository.ContentRepository
import dagger.android.support.DaggerFragment
import javax.inject.Inject

// TODO: Коммент
class HotContentFragment : DaggerFragment(R.layout.fragment_hot) {

    @Inject lateinit var repository: ContentRepository

}