package com.example.tinkoff_lab.other

import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.Placeholder
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tinkoff_lab.R
import kotlinx.android.synthetic.main.fragment_random.*

fun RecyclerView.disableOverScroll() {
    overScrollMode = View.OVER_SCROLL_NEVER
}

fun ImageView.loadWightGlide(fragment: Fragment, url: String?) {
    Glide.with(fragment)
        .load(url)
        .error(R.drawable.ic_error_red)
        .placeholder(R.drawable.png_splash)
        .into(this)
}