package com.mtszser.reminderapp.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    // User Reminder Table INTERFACE

    @Query("Select * from user_table")
    suspend fun getAll(): List<UserProfile>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(userProfile: UserProfile)

    @Delete
    suspend fun delete(userProfile: UserProfile)

    @Update
    suspend fun update(userProfile: UserProfile)

    // Water Reminder Table INTERFACE

    @Query("Select waterContainer, waterId, alreadyDrank from user_table ")
    suspend fun getWaterReminder(): WaterReminder

    // Action Reminder Table INTERFACE

    @Query("Select actionId, actionDesc, actionName from user_table ")
    suspend fun getActionReminder(): ActionReminder

    @Query("Update user_table set alreadyDrank = alreadyDrank + :drunkWater")
    suspend fun updateWater(drunkWater: Int)


//    @Query("Select actionReminder from user_table")
//    suspend fun getActionReminder(): List<ActionReminder>


}