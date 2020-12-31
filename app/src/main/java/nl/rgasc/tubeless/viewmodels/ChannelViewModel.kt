package nl.rgasc.tubeless.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nl.rgasc.tubeless.db.ChannelRepository
import nl.rgasc.tubeless.models.Channel

class ChannelViewModel(application: Application) : AndroidViewModel(application) {

    private val channelRepository = ChannelRepository(application.applicationContext)
    private val _currentChannel: MutableLiveData<Channel> = MutableLiveData()

    val channels: LiveData<List<Channel>> = channelRepository.getAllChannels()
    var currentChannel: Channel
        get() = _currentChannel.value!!
        set(channel) { _currentChannel.value = channel }
    val error = MutableLiveData<String>()
    val success = MutableLiveData<Boolean>()

    fun insertChannel(channel: Channel) {
        if (channel.channelId.isBlank()) {
            error.value = "Insert a channel name"
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            channelRepository.insertChannel(channel)
        }

        success.value = true
    }

    fun deleteChannel(channel: Channel) {
        CoroutineScope(Dispatchers.IO).launch {
            channelRepository.deleteChannel(channel)
        }
    }
}