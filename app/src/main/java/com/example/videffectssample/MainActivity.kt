package com.example.videffectssample

import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.videffectssample.databinding.ActivityMainBinding
import com.sherazkhilji.videffects.BlackAndWhiteEffect
import com.sherazkhilji.videffects.DuotoneEffect
import com.sherazkhilji.videffects.GrainEffect
import com.sherazkhilji.videffects.filter.AutoFixFilter


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mMediaPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        mMediaPlayer = MediaPlayer()
        try {
            val afd = assets.openFd("sample.mp4")
            mMediaPlayer.setDataSource(
                afd.fileDescriptor,
                afd.startOffset, afd.length
            )
        } catch (e: Exception) {
            Log.e(TAG, e.message, e)
        }

        binding.mVideoSurfaceView.init(mMediaPlayer, AutoFixFilter())

        //Black and White Effect
        binding.bwButton.setOnClickListener {
            binding.mVideoSurfaceView.shader = BlackAndWhiteEffect()
        }
        //Grain Effect
        binding.grainButton.setOnClickListener {
            binding.mVideoSurfaceView.shader = GrainEffect(0.50f)
        }
        //Duotone Effect
        binding.duotoneButton.setOnClickListener {
            binding.mVideoSurfaceView.shader =
                DuotoneEffect(Color.BLUE, Color.YELLOW)
        }
        //Reset with AutoFixFilter
        binding.resetButton.setOnClickListener {
            binding.mVideoSurfaceView.filter = AutoFixFilter()
        }

    }

    companion object {
        private const val TAG = "MainActivity"
    }
}