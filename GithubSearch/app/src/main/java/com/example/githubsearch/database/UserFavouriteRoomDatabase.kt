package com.example.githubsearch.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserFavourite::class], version = 1)
abstract class UserFavouriteRoomDatabase : RoomDatabase() {
    abstract fun userFavouriteDao(): UserFavouriteDao

    companion object {
        @Volatile
        private var INSTANCE: UserFavouriteRoomDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): UserFavouriteRoomDatabase {
            if (INSTANCE == null) {
                synchronized(UserFavouriteRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        UserFavouriteRoomDatabase::class.java, "userFavourite_database"
                    ).build()
                }
            }
            return INSTANCE as UserFavouriteRoomDatabase
        }
    }
}