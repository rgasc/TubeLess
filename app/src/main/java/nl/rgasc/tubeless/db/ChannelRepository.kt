package nl.rgasc.tubeless.db

import android.content.Context
import androidx.lifecycle.LiveData
import nl.rgasc.tubeless.models.Channel

class ChannelRepository(context: Context) {

    private val channelDao: ChannelDao

    init {
        val database = ChannelRoomDatabase.getDatabase(context)
        channelDao = database!!.channelDao()
    }

    fun getAllChannels(): LiveData<List<Channel>> {
        return channelDao.getAllChannels()
    }

    suspend fun insertChannel(channel: Channel) {
        channelDao.insertChannel(channel)
    }

    suspend fun deleteChannel(channel: Channel) {
        channelDao.deleteChannel(channel)
    }
}