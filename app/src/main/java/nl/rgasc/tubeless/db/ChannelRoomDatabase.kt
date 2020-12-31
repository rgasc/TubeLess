package nl.rgasc.tubeless.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import nl.rgasc.tubeless.models.Channel

@Database(entities = [Channel::class], version = 1, exportSchema = false)
abstract class ChannelRoomDatabase : RoomDatabase() {

    abstract fun channelDao(): ChannelDao

    companion object {
        private const val DATABASE_NAME = "CHANNEL_DATABASE"

        @Volatile
        private var INSTANCE: ChannelRoomDatabase? = null

        fun getDatabase(context: Context): ChannelRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(ChannelRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            ChannelRoomDatabase::class.java, DATABASE_NAME
                        ).build()
                    }
                }
            }
            return INSTANCE
        }
    }
}
