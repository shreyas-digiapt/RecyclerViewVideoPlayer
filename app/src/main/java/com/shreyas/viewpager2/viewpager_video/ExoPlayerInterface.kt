package com.shreyas.viewpager2.viewpager_video

import com.google.android.exoplayer2.SimpleExoPlayer

interface ExoPlayerInterface {
    fun exoPlayerInterface(
        holder: ViewPageVideoAdapter.ViewPagerVideoHolder,
        position: Int
//        player: SimpleExoPlayer?
    )
}