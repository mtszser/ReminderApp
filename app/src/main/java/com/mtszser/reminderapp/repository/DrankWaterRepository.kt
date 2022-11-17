package com.mtszser.reminderapp.repository

import com.mtszser.reminderapp.R
import com.mtszser.reminderapp.model.DrankWaterBase
import com.mtszser.reminderapp.model.DrankWaterDao
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class DrankWaterRepository @Inject constructor(private val drankWaterDao: DrankWaterDao ) {

    suspend fun getAddedWater(): List<DrankWaterBase> {
        return drankWaterDao.getAddedWater()
    }

    suspend fun insertWaterContainer(waterContainer: DrankWaterBase) {
        drankWaterDao.insertWaterContainer(waterContainer)
    }

    suspend fun deleteAddedWater(drankWaterBase: DrankWaterBase) {
        drankWaterDao.deleteAddedWater(drankWaterBase)

    }




    fun getDate(): String {
        val time = Calendar.getInstance().time
        val formatter = SimpleDateFormat("hh:mm a")
        return formatter.format(time)
    }

}