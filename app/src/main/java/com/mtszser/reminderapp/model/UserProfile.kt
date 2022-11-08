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
    @Embedded val drankWaterBase: DrankWaterBase,
)
data class WaterReminder(
    @PrimaryKey(autoGenerate = true) val waterId: Int,
    val waterContainer: Int,
    val bonusWaterContainer: Int,
    val alreadyDrank: Int,
    val currentDate: String,
)

fun UserProfile.maptoView() = UserProfileView(
    id = this.id,
    firstName = this.firstName,
    weight = this.weight,
    height = this.height,
    containerID = this.containerID,
    waterReminder = this.waterReminder,
    drankWaterBase = this.drankWaterBase,
)


