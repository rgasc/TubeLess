package nl.rgasc.tubeless.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import nl.rgasc.tubeless.models.Channel

@Dao
interface ChannelDao {

    @Query("SELECT * FROM channels")
    fun getAllChannels(): LiveData<List<Channel>>

    @Insert
    suspend fun insertChannel(channel: Channel)

    @Delete
    suspend fun deleteChannel(channel: Channel)
}