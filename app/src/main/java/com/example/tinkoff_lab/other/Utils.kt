package com.example.tinkoff_lab.other

import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_random.*

fun RecyclerView.disableOverScroll() {
    overScrollMode = View.OVER_SCROLL_NEVER
}

fun ImageView.loadWightGlide(fragment: Fragment, url: String) {
    Glide.with(fragment)
        .load(url)
        .into(this)
}