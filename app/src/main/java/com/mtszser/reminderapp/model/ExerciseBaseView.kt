package com.mtszser.reminderapp.model

data class ExerciseBaseView(
    val ebvID: Int,
    val ebvName: String,
    val ebvImage: Int,
    val ebvBonusActivityWater: Int,

)
fun ExerciseBaseView.mapToDatabase() = ExerciseBase(
    activityID = this.ebvID,
    activityImage = this.ebvImage,
    activityName = this.ebvName,
    bonusActivityWater = this.ebvBonusActivityWater
)
