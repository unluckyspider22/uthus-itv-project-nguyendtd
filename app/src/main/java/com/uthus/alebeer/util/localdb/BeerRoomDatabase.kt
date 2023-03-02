package com.uthus.alebeer.util.localdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.uthus.alebeer.data.datasource.local.BeerDao
import com.uthus.alebeer.data.entity.BeerEntity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


@Database(entities = [BeerEntity::class], version = 1, exportSchema = false)
abstract class BeerRoomDatabase : RoomDatabase() {
    abstract fun beerDao(): BeerDao?

    companion object {
        private const val NUMBER_OF_THREADS = 4
        val databaseWriteExecutor: ExecutorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS)
        private var INSTANCE: BeerRoomDatabase? = null
        fun getDatabase(context: Context): BeerRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(BeerRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = databaseBuilder(
                            context.applicationContext,
                            BeerRoomDatabase::class.java, "word_database"
                        )
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}