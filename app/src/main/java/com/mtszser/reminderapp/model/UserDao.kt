package com.mtszser.reminderapp.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Query("Select * from user_table")
    fun getAll(): LiveData<List<UserProfile>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(userProfile: UserProfile)

    @Delete
    fun delete(userProfile: UserProfile)

    @Update
    fun update(userProfile: UserProfile)



}