package com.example.githubsearch.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserFavouriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: UserFavourite)

    @Update
    fun update(user: UserFavourite)

    @Delete
    fun delete(user: UserFavourite)

    @Query("SELECT * FROM userFavourite ORDER BY id asc")
    fun getAllUserFavourites(): LiveData<List<UserFavourite>>

    @Query("SELECT * FROM userFavourite WHERE login = :login")
    fun getUserFavouriteByUsername(login: String?): LiveData<List<UserFavourite>>
}