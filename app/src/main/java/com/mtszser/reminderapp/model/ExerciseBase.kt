package com.mtszser.reminderapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise_table")
data class ExerciseBase(
    @PrimaryKey(autoGenerate = true)val exerciseID: Int,
    @ColumnInfo val exerciseName: String,
    @ColumnInfo val exerciseDuration: Int
)
