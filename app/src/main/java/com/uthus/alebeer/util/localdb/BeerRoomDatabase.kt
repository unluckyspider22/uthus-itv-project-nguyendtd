package com.uthus.alebeer.util.localdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.uthus.alebeer.data.datasource.local.BeerDao
import com.uthus.alebeer.data.entity.BeerEntity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


@Database(entities = [BeerEntity::class], version = 2, exportSchema = true)
abstract class BeerRoomDatabase : RoomDatabase() {
    abstract fun beerDao(): BeerDao

    companion object {
        private const val NUMBER_OF_THREADS = 4
        val databaseWriteExecutor: ExecutorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS)
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE Contact ADD COLUMN seller_id TEXT NOT NULL DEFAULT ''")
            }
        }
        @Volatile
        private var INSTANCE: BeerRoomDatabase? = null
        fun getDatabase(context: Context): BeerRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = databaseBuilder(
                    context.applicationContext,
                    BeerRoomDatabase::class.java,
                    "word_database"
                )
                    .addMigrations(MIGRATION_1_2)
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

}
