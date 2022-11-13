package com.mtszser.reminderapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mtszser.reminderapp.viewmodel.getDate

@Entity(tableName = "exercise_table")
data class ExerciseBase(
    @PrimaryKey(autoGenerate = true)val activityID: Int = 0,
    val activityName: String,
    val bonusActivityWater: Int,
    val activityDate: String = getDate()
)

fun ExerciseBase.mapToView() = ExerciseBaseView(
    ebvID = this.activityID,
    ebvName = this.activityName,
    ebvBonusActivityWater = this.bonusActivityWater,
    ebvActivityDate = this.activityDate
)
