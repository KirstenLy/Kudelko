package com.example.tinkoff_lab.ui.random

import android.os.Bundle
import android.view.View
import com.example.tinkoff_lab.R
import com.example.tinkoff_lab.common.ContentVM
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/** Screen to display one random content item */
class RandomContentFragment : DaggerFragment(R.layout.fragment_hot) {

    @Inject lateinit var contentVM: ContentVM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}