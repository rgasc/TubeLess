package nl.rgasc.tubeless.api

import nl.rgasc.tubeless.models.RSSResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface VideoApiService {

    @GET("videos.xml")
    suspend fun getVideos(@Query("channel_id") channelId: String) : RSSResponse.Feed
}