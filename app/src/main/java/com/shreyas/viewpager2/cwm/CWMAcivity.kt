package com.shreyas.viewpager2.cwm

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.HorizontalScrollView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.shreyas.viewpager2.R
import com.shreyas.viewpager2.cwm.adapter.VerticalSpacingItemDecorator
import com.shreyas.viewpager2.cwm.adapter.VideoPlayerAdapter
import com.shreyas.viewpager2.cwm.adapter.VideoPlayerRecyclerView
import java.util.*
import kotlin.collections.ArrayList


class CWMAcivity : AppCompatActivity() {

    lateinit var customRV: VideoPlayerRecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_c_w_m)

        customRV = findViewById(R.id.recycler_view)

        initializeView()
    }

    private fun initializeView() {
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        customRV.layoutManager = layoutManager

        val decor = VerticalSpacingItemDecorator(10)
        customRV.addItemDecoration(decor)

        val mediaObject: ArrayList<MediaData> = ArrayList(Arrays.asList(*Resources.MEDIA_OBJECTS))
        Log.d("data_test", "dta: "+mediaObject[0].thumbnail)
        customRV.setMediaObjects(mediaObject)

        val helper = PagerSnapHelper()
        helper.attachToRecyclerView(customRV)
        val adpter = VideoPlayerAdapter(mediaObject, initGLide())
//        (customRV.layoutManager as LinearLayoutManager).smoothScrollToPosition(customRV, null, 2)
        customRV.smoothScrollBy(1, 0) //very very imprtant to start video play for 0 index
        customRV.visibility = View.VISIBLE
        customRV.adapter = adpter
    }

    private fun initGLide(): RequestManager {
        val options: RequestOptions =
            RequestOptions().placeholder(R.drawable.ic_image_error).error(R.drawable.ic_image_error)
        return Glide.with(this).setDefaultRequestOptions(options)
    }

    override fun onDestroy() {
        super.onDestroy()

        customRV.releasePlayer()
    }
}
