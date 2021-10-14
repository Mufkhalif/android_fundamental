package com.example.movieapps.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room
import com.example.movieapps.data.local.entity.Movie
import com.example.movieapps.data.local.entity.Tv

@Database(entities = [Movie::class, Tv::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: MovieDatabase? = null

        fun getInstance(context: Context): MovieDatabase = INSTANCE ?: synchronized(this) {
            Room.databaseBuilder(
                context.applicationContext,
                MovieDatabase::class.java,
                "Movies.db"
            ).build().apply { INSTANCE = this }
        }
    }
}