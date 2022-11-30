package com.mtszser.reminderapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mtszser.reminderapp.viewmodel.getDate

@Entity(tableName = "exercise_table")
data class ExerciseBase(
    @PrimaryKey(autoGenerate = true) val activityID: Int = 0,
    val activityName: String,
    val bonusActivityWater: Int,
    val activityImage: Int,
) {
    override fun toString(): String {
        return activityName
    }
}

fun ExerciseBase.mapToView() = ExerciseBaseView(
    ebvID = this.activityID,
    ebvName = this.activityName,
    ebvImage = this.activityImage,
    ebvBonusActivityWater = this.bonusActivityWater,
)

data class BaseActivities(
    val baseActivityID: Int,
    val baseActivityName: String,
    val baseActivityDesc: String,
    val baseActivityBonusCalories: Int,
) {
    override fun toString(): String {
        return baseActivityName
    }
}
