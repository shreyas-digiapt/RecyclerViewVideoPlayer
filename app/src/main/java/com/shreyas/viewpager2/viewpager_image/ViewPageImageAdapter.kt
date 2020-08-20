package com.shreyas.viewpager2.viewpager_image

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.shreyas.viewpager2.R
import kotlinx.android.synthetic.main.single_image_snippet.view.*

class ViewPageImageAdapter(
    var imageList:List<Int>,
    var context: Context
) : RecyclerView.Adapter<ViewPageImageAdapter.ViewPagerHolder>() {

    var exoplayerInterface:PlayerInterface = context as PlayerInterface

    inner class ViewPagerHolder(itemView:View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.single_image_snippet, parent, false)
        return ViewPagerHolder(view)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: ViewPagerHolder, position: Int) {
        Log.d("vp_test", "position:Adapter ${position}")
        exoplayerInterface.playerInterface(holder, position)
    }


}