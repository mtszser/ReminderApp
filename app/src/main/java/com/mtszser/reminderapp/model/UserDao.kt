package com.mtszser.reminderapp.model

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

    @Query("Select waterContainer, waterId, alreadyDrank, currentDate, bonusWaterContainer from user_table ")
    suspend fun getWaterReminder(): WaterReminder

    @Query("Update user_table set currentDate = :currentDay")
    suspend fun updateDate(currentDay: String)

    @Query("Select currentDate from user_table")
    suspend fun getProfileDate(): String

    @Query("Select * from user_table")
    suspend fun getUserProfile(): UserProfile

    @Query("Update user_table set bonusWaterContainer = bonusWaterContainer + :exerciseWaterIntake")
    suspend fun addToBonusWaterContainer(exerciseWaterIntake: Int)

    @Query("Select * from exercise_table")
    suspend fun getActivityList(): List<ExerciseBase>

    @Query("Update user_table set bonusWaterContainer = bonusWaterContainer - :exerciseWaterIntake")
    suspend fun deleteBonusExerciseWater(exerciseWaterIntake: Int)


    // Action Reminder Table INTERFACE


    @Query("Update user_table set alreadyDrank = alreadyDrank + :drunkWater")
    suspend fun addWater(drunkWater: Int)

    @Query("Update user_table set alreadyDrank = alreadyDrank - :drunkWater")
    suspend fun deleteWater(drunkWater: Int)

    @Query("Update user_table set alreadyDrank = 0, bonusWaterContainer = 0")
    suspend fun resetWater()

    @Query("Update user_table set alreadyDrank = 0")
    suspend fun resetDrankWater()



//    @Query("Select actionReminder from user_table")
//    suspend fun getActionReminder(): List<ActionReminder>

    @Query("Update user_table set containerId = :spinnerPos")
    suspend fun saveSpinnerPos(spinnerPos: Int)

    @Query("Select containerId from user_table")
    suspend fun getContainerPos(): Int


}


