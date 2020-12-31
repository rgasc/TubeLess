package nl.rgasc.tubeless.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.withTimeout
import nl.rgasc.tubeless.models.Channel
import nl.rgasc.tubeless.models.Video
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class VideoRepository {

    private val videoApiService: VideoApiService = VideoApi.createApi()
    private val _videos: MutableLiveData<List<Video>> = MutableLiveData()

    val videos: LiveData<List<Video>> get() = _videos

    suspend fun getVideos(channels: List<Channel>) {
        val videos: ArrayList<Video> = arrayListOf()

        channels.forEach {
            try {
                val response = withTimeout(5_000) {
                    videoApiService.getVideos(it.channelId)
                }

                response.entryList?.forEach { entry ->
                    videos.add(
                        Video(
                            videoUrl = entry.videoUrl,
                            thumbnailUrl = entry.group?.thumbnail?.thumbnailUrl!!,
                            title = entry.group?.title!!,
                            channelName = entry.author?.channelName!!,
                            views = entry.group?.community?.statistics?.views!!,
                            uploaded = SimpleDateFormat(
                                "yyyy-MM-dd'T'HH:mm:ss'+'ss:ss",
                                Locale.ENGLISH
                            ).parse(entry.uploaded)!!
                        )
                    )
                }
            } catch (error: Exception) {
                Log.e("Video API error", error.toString())
            }
        }

        _videos.value = videos
    }
}