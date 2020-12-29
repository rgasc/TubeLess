package nl.rgasc.tubeless.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "channels")
data class Channel(

        @ColumnInfo(name = "name")
        var name: String,

        @ColumnInfo(name = "channel_id")
        var channelId: String,

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Long? = null
)
