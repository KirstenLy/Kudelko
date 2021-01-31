package com.example.tinkoff_lab.ui.random

import android.os.Bundle
import android.view.View
import com.example.tinkoff_lab.R
import com.example.tinkoff_lab.common.ContentVM
import com.example.tinkoff_lab.data.models.ContentModel
import com.example.tinkoff_lab.other.loadWightGlide
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_random.*
import javax.inject.Inject

/** Screen to display one random content item */
class RandomContentFragment : DaggerFragment(R.layout.fragment_random) {

    @Inject lateinit var contentVM: ContentVM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        contentVM.contentSingleItemLiveData.observe(viewLifecycleOwner, {
            setContent(it.first())
        })
    }

    private fun setContent(content: ContentModel) {
        content_img.loadWightGlide(this, content.gifURL)
    }
}