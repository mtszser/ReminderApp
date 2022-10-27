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


    fun insertSpinnerData(): ArrayList<DrankWaterBase> = arrayListOf(
            DrankWaterBase(0, 20, R.drawable.ic_soft_drink_straw_20ml, "",),
            DrankWaterBase(0, 200, R.drawable.ic_glass_of_water_200ml, ""),
            DrankWaterBase(0, 250, R.drawable.ic_glass_of_water_250ml, ""),
            DrankWaterBase(0, 330, R.drawable.ic_can_soda_330ml, ""),
            DrankWaterBase(0, 500, R.drawable.ic_water_bottle, ""),
            DrankWaterBase(0, 750, R.drawable.ic_water_bottle_750ml, ""),
            DrankWaterBase(0, 1000, R.drawable.ic_water_bottle_1l, "")
        )


    fun getDate(): String {
        val time = Calendar.getInstance().time
        val formatter = SimpleDateFormat("hh:mm a")
        return formatter.format(time)
    }

}