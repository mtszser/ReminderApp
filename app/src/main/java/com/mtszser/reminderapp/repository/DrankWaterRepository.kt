package com.mtszser.reminderapp.repository

import com.mtszser.reminderapp.R
import com.mtszser.reminderapp.model.DrankWaterBase
import com.mtszser.reminderapp.model.DrankWaterDao
import com.mtszser.reminderapp.model.WaterContainers
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class DrankWaterRepository @Inject constructor(private val drankWaterDao: DrankWaterDao ) {

    suspend fun getAllWater(): List<DrankWaterBase> {
        return drankWaterDao.getAllWater()
    }

    suspend fun insertWaterContainer(waterContainer: DrankWaterBase) {
        drankWaterDao.insertWaterContainer(waterContainer)
    }


    fun insertSpinnerData(): ArrayList<DrankWaterBase> {
        val containers = ArrayList<DrankWaterBase>()
        containers.apply {
            add(DrankWaterBase(0, 20, R.drawable.ic_soft_drink_straw_20ml))
            add(DrankWaterBase(1, 200, R.drawable.ic_glass_of_water_200ml))
            add(DrankWaterBase(2, 250, R.drawable.ic_glass_of_water_250ml))
            add(DrankWaterBase(3, 330, R.drawable.ic_can_soda_330ml))
            add(DrankWaterBase(4, 500, R.drawable.ic_water_bottle))
            add(DrankWaterBase(5, 750, R.drawable.ic_water_bottle_750ml))
            add(DrankWaterBase(6, 1000, R.drawable.ic_water_bottle_1l))
        }
        return containers
    }


    fun getDate(): String {
        val time = Calendar.getInstance().time
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        return formatter.format(time)
    }

}