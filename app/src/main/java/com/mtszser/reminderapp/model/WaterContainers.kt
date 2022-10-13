package com.mtszser.reminderapp.model


data class WaterContainers(
    val containerID: String,
    val containerCapacity: String,
    val containerImg: Int,
)


//data class WaterContainers(
//    val containerID: String,
//    val containerCapacity: String,
//    val containerURI: String,
//){
//    override fun toString(): String{
//        return containerCapacity + "ml"
//    }
//
//}