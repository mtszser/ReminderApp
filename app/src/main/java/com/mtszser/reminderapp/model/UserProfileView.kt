package com.mtszser.reminderapp.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class UserProfileView(
    val id: Int,
    val firstName: String?,
    val weight: String?,
    val height: String?,
    val containerID: Int?,
    val waterReminder: WaterReminder?,
    val drankWaterBase: DrankWaterBase,
)


