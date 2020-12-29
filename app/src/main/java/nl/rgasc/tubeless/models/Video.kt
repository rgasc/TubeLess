package nl.rgasc.tubeless.models

import java.util.*

data class Video(

    var videoUrl: String,
    var thumbnailUrl: String,
    var title: String,
    var channelName: String,
    var views: String,
    var uploaded: Date
)