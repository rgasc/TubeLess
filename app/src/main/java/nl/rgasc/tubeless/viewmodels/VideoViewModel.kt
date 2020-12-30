package nl.rgasc.tubeless.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nl.rgasc.tubeless.api.VideoRepository
import nl.rgasc.tubeless.models.Channel

class VideoViewModel(application: Application) : AndroidViewModel(application) {

    private val videoRepository = VideoRepository()

    val videos = videoRepository.videos

    fun getVideos(channels: List<Channel>) {
        viewModelScope.launch {
            try {
                channels.forEach {
                    videoRepository.getVideos(it.channelId)
                }
            } catch (error: VideoRepository.VideoApiError) {
                Log.e("Video API error", error.cause.toString())
            }
        }
    }
}