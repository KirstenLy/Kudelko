package com.example.tinkoff_lab

import android.view.View
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.disableOverScroll() {
    overScrollMode = View.OVER_SCROLL_NEVER
}