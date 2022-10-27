package com.mtszser.reminderapp.model


data class DrankWaterView(
    val dwID: Int,
    val dwCap: Int,
    val dwImg: Int,
    val dwDate: String
) {

    fun getCap(): String {
        return dwCap.toString() + "ml"
    }

}
