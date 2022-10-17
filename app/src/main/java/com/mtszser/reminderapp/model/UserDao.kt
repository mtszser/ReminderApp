package com.mtszser.reminderapp.model

import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.*

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

    @Query("Select waterContainer, waterId, alreadyDrank, currentDate from user_table ")
    suspend fun getWaterReminder(): WaterReminder

    @Query("Update user_table set currentDate = :currentDay")
    suspend fun updateDate(currentDay: String)

    @Query("Select currentDate from user_table")
    suspend fun getProfileDate(): String




    // Action Reminder Table INTERFACE

    @Query("Select actionId, actionDesc, actionName from user_table ")
    suspend fun getActionReminder(): ActionReminder

    @Query("Update user_table set alreadyDrank = alreadyDrank + :drunkWater")
    suspend fun addWater(drunkWater: Int)

    @Query("Update user_table set alreadyDrank = alreadyDrank - :drunkWater")
    suspend fun deleteWater(drunkWater: Int)


    @Query("Update user_table set alreadyDrank = 0")
    suspend fun resetWater()

//    @Query("Select actionReminder from user_table")
//    suspend fun getActionReminder(): List<ActionReminder>

    @Query("Update user_table set containerId = :spinnerPos")
    suspend fun saveSpinnerPos(spinnerPos: Int)

    @Query("Select containerId from user_table")
    suspend fun getContainerPos(): Int

}