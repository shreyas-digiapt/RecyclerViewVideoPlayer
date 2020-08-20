package com.shreyas.viewpager2.cwm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.shreyas.viewpager2.R
import com.shreyas.viewpager2.cwm.MediaData

class VideoPlayerAdapter(var  mediaList:ArrayList<MediaData>, var requestManager: RequestManager) : RecyclerView.Adapter<VideoPlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoPlayerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cmw_single_snippet, parent, false)
        return VideoPlayerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mediaList.size
    }

    override fun onBindViewHolder(holder: VideoPlayerViewHolder, position: Int) {
        holder.onBind(mediaList[position], requestManager)
    }

}