package com.example.tinkoff_lab.ui.hot

import com.example.tinkoff_lab.R
import com.example.tinkoff_lab.common.ContentVM
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/** Fragment with "Hot" category content, not supported yet */
// TODO: Поддержать "Горячее"
class HotContentFragment : DaggerFragment(R.layout.fragment_hot) {

    @Inject lateinit var contentVM: ContentVM
}