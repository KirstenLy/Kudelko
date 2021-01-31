package com.example.tinkoff_lab.ui.random

import android.os.Bundle
import android.view.View
import com.example.tinkoff_lab.R
import com.example.tinkoff_lab.common.BackAndForwardButtonState
import com.example.tinkoff_lab.common.ContentVM
import com.example.tinkoff_lab.data.models.ContentModel
import com.example.tinkoff_lab.loadWithGlide
import com.google.android.material.snackbar.Snackbar
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
        contentVM.errorEvent.observe(viewLifecycleOwner, ::showError)
        contentVM.backAndForwardBtnStateEvent.observe(viewLifecycleOwner, ::setButtonsState)
    }

    private fun setContent(content: ContentModel) {
        with(content) {
            content_description.text = if (description.isNotEmpty()) {
                description
            } else {
                getString(R.string.random_content_no_description)
            }
            content_img.loadWithGlide(this@RandomContentFragment, gifURL)
        }
    }

    private fun showError(error: String) {
        Snackbar.make(requireView(), error, Snackbar.LENGTH_LONG).show()
        if (content_img.drawable == null) {
            content_img.loadWithGlide(this, null)
        }
    }

    private fun setButtonsState(state: BackAndForwardButtonState) {
        content_back.isEnabled = state.isBackButtonEnabled
        content_next.isEnabled = state.isForwardButtonEnabled
    }
}