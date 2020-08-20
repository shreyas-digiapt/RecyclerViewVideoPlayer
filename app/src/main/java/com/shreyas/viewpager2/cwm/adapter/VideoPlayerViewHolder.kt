package com.shreyas.viewpager2.cwm.adapter

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.shreyas.viewpager2.R
import com.shreyas.viewpager2.cwm.MediaData
import kotlinx.android.synthetic.main.cmw_single_snippet.view.*

public class VideoPlayerViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {

    var thumbnail: ImageView = itemView.findViewById(R.id.thumbnail)
    var progressBar: ProgressBar = itemView.findViewById(R.id.progressBar)
    var volumeControl: ImageView = itemView.findViewById(R.id.volume_control)
    lateinit var requestManager:RequestManager

   public fun onBind(mediaData:MediaData, requestManager:RequestManager) {
       this.requestManager = requestManager
       itemView.tag = this
       itemView.title.setText(mediaData.title)
       this.requestManager.load(mediaData.thumbnail).into(itemView.thumbnail)
   }
}