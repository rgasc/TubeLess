package nl.rgasc.tubeless.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import nl.rgasc.tubeless.models.Channel

class ChannelViewModel(application: Application) : AndroidViewModel(application) {

    private val _currentChannel: MutableLiveData<Channel> = MutableLiveData()

    var currentChannel: Channel
        get() = _currentChannel.value!!
        set(channel) { _currentChannel.value = channel }
}