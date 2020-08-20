package com.shreyas.viewpager2.viewpager_video

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
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
import java.lang.Exception
import java.lang.IllegalStateException
import kotlin.properties.Delegates

class ViewPageVideoAdapter(
    var videoList: List<String>,
    var context: Context
//    var player: SimpleExoPlayer
    ) : RecyclerView.Adapter<ViewPageVideoAdapter.ViewPagerVideoHolder>()
//    , Player.EventListener
{

//    lateinit var mPlayer: PlayerView
//    lateinit var mediaSource: MediaSource
//    lateinit var trackSelector: TrackSelector
//    lateinit var dataSourceFactory: DataSource.Factory
//    lateinit var loadControl: LoadControl
//    lateinit var adaptiveFactory: AdaptiveTrackSelection.Factory
//    lateinit var player: SimpleExoPlayer
//    lateinit var holderr: ViewPagerVideoHolder

//    lateinit var progressBar: ProgressBar

//    private var parent1:RecyclerView? = null

//    var pos by Delegates.notNull<Int>()


    val exoPlayerInterface:ExoPlayerInterface = context as ExoPlayerInterface

//    public fun setPostion(postion:Int) {
//        this.pos = postion
//        Log.d("pos-test","test: data")
//        initializePlayer(videoList[postion])
//    }

    inner class ViewPagerVideoHolder(itemView:View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerVideoHolder {
//        this.parent1 = parent as RecyclerView
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_video_snippet,parent, false)
        return ViewPagerVideoHolder(view)
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

//    override fun onViewRecycled(holder: ViewPagerVideoHolder) {
//        parent1?.recycledViewPool?.clear()
//    }

    override fun onBindViewHolder(holder: ViewPagerVideoHolder, position: Int) {
//        parent1?.recycledViewPool?.clear()
        Log.d("test_postion", "postion: ${position}")
//        progressBar = holder.itemView.findViewById(R.id.pb)
//        mPlayer = holder.itemView.findViewById(R.id.playerView)
//        initializePlayer(videoList[position])
        Log.d("pos-test","test: binder")
        exoPlayerInterface.exoPlayerInterface(holder, position)
    }

//    private fun initializePlayer(uri:String) {
//        try {
//            if (player != null) {
//                player.playWhenReady = false
//            }
//        }catch (e:Exception) {
//            Log.d("player_error", "exception:  ${e.message}")
//        }
//        loadControl = DefaultLoadControl.Builder().setBufferDurationsMs(
//            DefaultLoadControl.DEFAULT_MIN_BUFFER_MS,
//            DefaultLoadControl.DEFAULT_MAX_BUFFER_MS,
//            DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_MS,
//            DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_AFTER_REBUFFER_MS
//        ).createDefaultLoadControl()
//
//        adaptiveFactory = AdaptiveTrackSelection.Factory()
//
//        trackSelector = DefaultTrackSelector(adaptiveFactory)
//
//        dataSourceFactory = DefaultDataSourceFactory(context, Util.getUserAgent(context, "myapp"))
//
//        player = ExoPlayerFactory.newSimpleInstance(context, trackSelector)
//
//        mediaSource = buildMediaSource(Uri.parse(uri), null)
//        player?.prepare(mediaSource, false, false)
//        mPlayer.player = player
//
//        player?.playWhenReady = true
//        player?.addListener(this)
//    }

//    private fun buildMediaSource(uri: Uri?, overrideExtension: String?): MediaSource {
//        @C.ContentType val type:Int = Util.inferContentType(uri)
//        when(type) {
//            C.TYPE_DASH ->
//                return DashMediaSource.Factory(dataSourceFactory).createMediaSource(uri)
//
//            C.TYPE_SS ->
//                return SsMediaSource.Factory(dataSourceFactory).createMediaSource(uri)
//
//            C.TYPE_HLS ->
//                return HlsMediaSource.Factory(dataSourceFactory).createMediaSource(uri)
//
//            C.TYPE_OTHER ->
//                return ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(uri)
//
//            else ->
//                throw IllegalStateException("Unsupported type: ${type}")
//        }
//    }

//    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
//        when(playbackState) {
//            Player.STATE_ENDED -> {
//                releasePlayer()
////                finish()
//            }
//
//            Player.STATE_READY -> {
//                progressBar.visibility = View.GONE
//            }
//
//            Player.STATE_BUFFERING -> {
//                progressBar.visibility = View.VISIBLE
//            }
//
//            Player.STATE_IDLE -> {
//
//            }
//        }
//    }


//    private fun releasePlayer() {
//        try {
//            player?.release()
//        }catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }

}