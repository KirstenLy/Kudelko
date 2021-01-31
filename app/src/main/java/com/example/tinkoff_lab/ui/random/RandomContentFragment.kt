package com.example.tinkoff_lab.ui.random

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
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
        initViews()
        initObservers()
    }

    private fun initViews() {
        content_back.setOnClickListener {
            contentVM.back()
        }

        content_next.setOnClickListener {
            contentVM.next()
        }
    }

    private fun initObservers() {
        contentVM.contentSingleItemLiveData.observe(viewLifecycleOwner, ::setContent)
        contentVM.isBackButtonVisibleLiveData.observe(viewLifecycleOwner, { isVisible -> content_back.isVisible = isVisible})
    }

    private fun setContent(content: ContentModel) {
        with(content) {
            content_description.text = if (description.isNotEmpty()) {
                description
            } else {
                getString(R.string.random_content_no_description)
            }
                content_img.loadWightGlide(this@RandomContentFragment, gifURL)
        }
    }

    private fun showEmptyImageError() {
        content_img.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_error_red))
    }
}