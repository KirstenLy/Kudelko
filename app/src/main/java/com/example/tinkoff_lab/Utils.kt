package com.example.tinkoff_lab

import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

private const val SIZE_THUMBNAIL = 300

fun ImageView.loadWithGlide(fragment: Fragment, url: String?) {
    Glide.with(fragment)
        .load(url ?: R.drawable.ic_error)
        .error(R.drawable.ic_error)
        .thumbnail(loadThumbnail(fragment))
        .into(this)
}

private fun loadThumbnail(fragment: Fragment) = Glide
    .with(fragment)
    .load(R.drawable.gif_loader)
    .override(SIZE_THUMBNAIL, SIZE_THUMBNAIL)