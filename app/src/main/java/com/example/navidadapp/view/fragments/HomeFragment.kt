package com.example.navidadapp.view.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.VideoView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.navidadapp.R
import com.example.navidadapp.viewModels.HomeViewModel
import com.example.navidadapp.viewModels.MainActivityViewModel

class HomeFragment : Fragment() {
    private lateinit var mainActivityViewModel : MainActivityViewModel
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var videoView: VideoView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        videoView = view.findViewById(R.id.home_video_view)
        mainActivityViewModel = ViewModelProvider(requireActivity())[MainActivityViewModel::class.java]
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        val videoPath = "android.resource://${requireActivity().packageName}/${R.raw.home_video}"
        videoView.setVideoPath(Uri.parse(videoPath).toString())
        videoView.setOnPreparedListener { mediaPlayer ->
            mediaPlayer.isLooping = true
            val lastPosition = mainActivityViewModel.getCurrentVideoPosition()
            videoView.seekTo(lastPosition)
        }
        return view
    }

    override fun onPause() {
        super.onPause()
        val currentPosition = videoView.currentPosition
        mainActivityViewModel.setCurrentVideoPosition(currentPosition)
        videoView.pause()
    }

    override fun onResume() {
        super.onResume()
        videoView.start()
    }
}