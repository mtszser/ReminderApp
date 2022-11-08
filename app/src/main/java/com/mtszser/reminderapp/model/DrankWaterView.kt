package com.mtszser.reminderapp.model


data class DrankWaterView(
    val dwID: Int,
    val dwCap: Int,
    val dwImg: Int,
    val dwDate: String
)

fun DrankWaterView.mapToDatabase() = DrankWaterBase(
    waterContCap = this.dwCap,
    waterContID = this.dwID,
    addedDate = this.dwDate,
    waterContImg = this.dwImg
)

