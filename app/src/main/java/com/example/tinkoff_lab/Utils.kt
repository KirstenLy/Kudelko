package com.example.tinkoff_lab

import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

fun ImageView.loadWightGlide(fragment: Fragment, url: String?) {
    Glide.with(fragment)
        .load(url ?: R.drawable.ic_error)
        .error(R.drawable.ic_error)
        .thumbnail(loadThumbnail(fragment))
        .into(this)
}

private fun loadThumbnail(fragment: Fragment) = Glide
    .with(fragment)
    .load(R.drawable.gif_loader)
    .override(300, 300)