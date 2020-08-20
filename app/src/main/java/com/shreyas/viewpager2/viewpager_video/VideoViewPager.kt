package com.shreyas.viewpager2.viewpager_video

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.viewpager2.widget.ViewPager2
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.source.dash.DashMediaSource
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.shreyas.viewpager2.R
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_video_view_pager.*
import kotlinx.android.synthetic.main.single_video_snippet.*

class VideoViewPager : AppCompatActivity(), ExoPlayerInterface, Player.EventListener {

    lateinit var videoList:List<String>

    var mPlayer: PlayerView? =null
    var mediaSource: MediaSource? = null
    var trackSelector: TrackSelector? = null
    var dataSourceFactory: DataSource.Factory? = null
    var loadControl: LoadControl? = null
    var adaptiveFactory: AdaptiveTrackSelection.Factory? = null
    var player:SimpleExoPlayer? = null

    lateinit var adapt:ViewPageVideoAdapter
//
    lateinit var progressBar: ProgressBar
//    lateinit var uri:String

    private var currentPos:Int? = null
    private var previousPos:Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_view_pager)

        videoList = listOf(
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",  "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",  "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"


        )

        Log.d("acitivty_test", "oncreate")

        viewPagerCAllbacks()
        adapt = ViewPageVideoAdapter(videoList, this@VideoViewPager)
        vp_video.apply {
            adapter = adapt
        }
    }

    private fun initializePlayer(uri: String) {
        try {
            Log.d("test_relasea", "sd: "+(player != null) )
            if (player != null) {
                removeVideoView(mPlayer, previousPos!!)
//                player?.seekTo(0)
//                player?.release()
//                player = null
//                mediaSource = null
//                trackSelector = null
            }
        }catch (e:java.lang.Exception){
            Log.d("exo_error","error: "+e.message)
        }
        loadControl = DefaultLoadControl.Builder().setBufferDurationsMs(
            DefaultLoadControl.DEFAULT_MIN_BUFFER_MS,
            DefaultLoadControl.DEFAULT_MAX_BUFFER_MS,
            DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_MS,
            DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_AFTER_REBUFFER_MS
        ).createDefaultLoadControl()

        adaptiveFactory = AdaptiveTrackSelection.Factory()

        trackSelector = DefaultTrackSelector(adaptiveFactory)

        dataSourceFactory = DefaultDataSourceFactory(this, Util.getUserAgent(this, "myapp"))

        player = ExoPlayerFactory.newSimpleInstance(this, trackSelector)

        mediaSource = buildMediaSource(Uri.parse(uri), null)
        player?.prepare(mediaSource, false, false)
        mPlayer?.player = player

        previousPos = currentPos;
        player?.playWhenReady = true
        player?.addListener(this)
    }

    private fun buildMediaSource(uri: Uri?, overrideExtension: String?): MediaSource {
        @C.ContentType val type:Int = Util.inferContentType(uri)
        when(type) {
            C.TYPE_DASH ->
                return DashMediaSource.Factory(dataSourceFactory).createMediaSource(uri)

            C.TYPE_SS ->
                return SsMediaSource.Factory(dataSourceFactory).createMediaSource(uri)

            C.TYPE_HLS ->
                return HlsMediaSource.Factory(dataSourceFactory).createMediaSource(uri)

            C.TYPE_OTHER ->
                return ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(uri)

            else ->
                throw IllegalStateException("Unsupported type: ${type}")
        }
    }

    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
        when(playbackState) {
            Player.STATE_ENDED -> {
//                releasePlayer()
//                finish()
            }

            Player.STATE_READY -> {
                progressBar.visibility = View.GONE
            }

            Player.STATE_BUFFERING -> {
                progressBar.visibility = View.VISIBLE
            }

            Player.STATE_IDLE -> {

            }
        }
    }

    private fun releasePlayer() {
        try {
            player?.release()
        }catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun exoPlayerInterface(
        holder: ViewPageVideoAdapter.ViewPagerVideoHolder,
        position: Int
//        playerr: SimpleExoPlayer?
    ) {
        Log.d("acitivty_test", "Interface")
//        player = playerr

        mPlayer = holder.itemView.findViewById(R.id.playerView)
        Log.d("test_player", "test:onInterface "+mPlayer)
        progressBar = holder.itemView.findViewById(R.id.pb)
//        uri = videoList[position]
    }

    override fun onPause() {
        super.onPause()

        Log.d("test_player", "test:onPause "+player)
        player?.playWhenReady = false
    }

    override fun onStart() {
        super.onStart()
    }

    private fun viewPagerCAllbacks() {
        vp_video.registerOnPageChangeCallback(
            object: ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                currentPos = position
                initializePlayer(videoList[position])
                Log.d("page_test_postion", "postion ${position}")
//                adapt.setPostion(position)
            }
        })
    }

    private fun removeVideoView(mPlayer:PlayerView?, previousPos:Int) {
        val parent = mPlayer?.parent as ViewGroup
        if (parent == null) {
            return
        }

//        val index = parent.indexOfChild(mPlayer)
        Log.d("pre_video_test", "pos: "+previousPos)
        parent.removeViewAt(previousPos)
    }


}
