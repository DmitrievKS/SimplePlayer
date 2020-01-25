package com.kirdmt.simpleplayer.ui.player

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.kirdmt.simpleplayer.R
import com.kirdmt.simpleplayer.data.TrackSearchResult
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_player.*
import java.util.*


class PlayerActivity : AppCompatActivity() {


    private var mediaPlayer: MediaPlayer? = MediaPlayer()
    private var pause: Boolean = false
    private val mHandler: Handler? = null
    private var mRunnable: Runnable? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        val trackSearchResult: TrackSearchResult = intent.getParcelableExtra("trackSearchResult")
        val trackUrl = trackSearchResult.previewUrl

        Picasso.with(this)
            .load(trackSearchResult.artworkUrl100)
            .placeholder(R.drawable.ic_search_white_24dp)
            .into(player_album_img)

        mediaPlayer?.apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build()
            ) //to send the object to the initialized state
            setDataSource(trackUrl) //to set media source and send the object to the initialized state
            prepareAsync() //to send the object to the prepared state, this may take time for fetching and decoding
            //prepare()
            //start() //to start the music and send the object to started state
        }
        mediaPlayer?.setOnPreparedListener(MediaPlayer.OnPreparedListener {
            seekbar_audio.max = mediaPlayer?.duration!!;
            refreshSeekBar();
            mediaPlayer?.start()

        })

        seekbar_audio.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, input: Boolean) {
                if (mediaPlayer != null && input) {

                    mediaPlayer?.seekTo(progress)

                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        })

    }

    override fun onResume() {
        super.onResume()
        if (!mediaPlayer?.isPlaying!!)
            mediaPlayer?.start()
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer?.stop()
    }


    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mHandler?.removeCallbacks(mRunnable)

    }

    fun onClickPlayPause(view: View) {
        if (mediaPlayer?.isPlaying!!) {
            changeButtonCondition()
            mediaPlayer?.pause()
        } else {

            if (mediaPlayer?.currentPosition!! >= mediaPlayer?.duration!!) {
                mediaPlayer?.seekTo(0)
                mediaPlayer?.start()
            }

            changeButtonCondition()
            mediaPlayer?.currentPosition?.let { mediaPlayer?.seekTo(it) }
            mediaPlayer?.start()
        }
    }

    private fun changeButtonCondition() {
        if (!pause) {
            pause = true
            button_play_pause.setText("Play")
        } else {
            pause = false
            button_play_pause.setText("Pause")
        }
    }

    private fun refreshSeekBar() {

        val timer = Timer()
        val monitor = object : TimerTask() {
            override fun run() {
                seekbar_audio.setProgress(mediaPlayer!!.currentPosition)
                if(!mediaPlayer?.isPlaying!! && !pause)
                {
                    mediaPlayer?.start()
                }
            }
        }
        timer.schedule(monitor, 1000, 1000)
    }

}