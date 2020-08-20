package com.shreyas.viewpager2.mutipleClicks

import android.view.View

fun View.setSafeOnClickListner(onSafeClick: (View) -> Unit) {

    val safeClickListener = SafeClickListener {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}