package nl.rgasc.tubeless.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nl.rgasc.tubeless.api.VideoRepository

class VideoViewModel(application: Application) : AndroidViewModel(application) {

    private val videoRepository = VideoRepository()

    val videos = videoRepository.videos

    fun getVideos(channelId: String) {
        viewModelScope.launch {
            try {
                videoRepository.getVideos(channelId)
            } catch (error: VideoRepository.VideoApiError) {
                Log.e("Video API error", error.cause.toString())
            }
        }
    }
}