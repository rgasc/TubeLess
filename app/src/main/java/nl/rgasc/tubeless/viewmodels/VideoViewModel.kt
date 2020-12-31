package nl.rgasc.tubeless.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nl.rgasc.tubeless.api.VideoRepository
import nl.rgasc.tubeless.models.Channel
import nl.rgasc.tubeless.models.Video

class VideoViewModel(application: Application) : AndroidViewModel(application) {

    private val videoRepository = VideoRepository()
    private val _currentVideo: MutableLiveData<Video> = MutableLiveData()
    val error = MutableLiveData<Boolean>()

    val videos = videoRepository.videos
    var currentVideo: Video
        get() = _currentVideo.value!!
        set(video) { _currentVideo.value = video }

    fun getVideos(channels: List<Channel>) {
        viewModelScope.launch {
            videoRepository.getVideos(channels)
            if (videos.value?.isEmpty() == true) error.value = true
        }
    }
}