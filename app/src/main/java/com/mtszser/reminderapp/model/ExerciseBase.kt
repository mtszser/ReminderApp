package com.mtszser.reminderapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise_table")
data class ExerciseBase(
    @PrimaryKey(autoGenerate = true)val activityID: Int,
    val activityName: String,
    val bonusActivityWater: Int,
)
