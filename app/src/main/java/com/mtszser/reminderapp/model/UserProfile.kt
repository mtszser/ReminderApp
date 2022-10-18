package com.mtszser.reminderapp.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserProfile(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "firstName") val firstName: String?,
    @ColumnInfo(name = "weight") val weight: String?,
    @ColumnInfo(name = "height") val height: String?,
    @ColumnInfo(name = "containerId") val containerID: Int?,
    @Embedded val waterReminder: WaterReminder?,
)
data class WaterReminder(
    @PrimaryKey(autoGenerate = true) val waterId: Int,
    val waterContainer: Int,
    val alreadyDrank: Int,
    val currentDate: String,
)


