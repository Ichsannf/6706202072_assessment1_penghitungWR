package org.d3if6706202072.assessment1.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [WrEntity::class], version = 1, exportSchema = false)
abstract class WrDb : RoomDatabase() {
    abstract val dao: WrDao
    companion object {
        @Volatile
        private var INSTANCE: WrDb? = null
        fun getInstance(context: Context): WrDb {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        WrDb::class.java,
                        "wr.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}