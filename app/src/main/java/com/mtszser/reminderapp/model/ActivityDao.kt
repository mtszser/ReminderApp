package com.mtszser.reminderapp.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ActivityDao {

    @Query("Select * from exercise_table")
    suspend fun getAllActivities(): List<ExerciseBase>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertExercise(exerciseBase: ExerciseBase)

    @Query("Select activityID, activityName, bonusActivityWater, activityImage from exercise_table")
    suspend fun getActivity(): ExerciseBase


}