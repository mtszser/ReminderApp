package com.mtszser.reminderapp.util

import com.mtszser.reminderapp.R
import com.mtszser.reminderapp.model.DrankWaterBase

object Const {
    fun insertSpinnerData(): ArrayList<DrankWaterBase> = arrayListOf(
        DrankWaterBase(waterContCap = 20, waterContImg = R.drawable.ic_soft_drink_straw_20ml),
        DrankWaterBase(waterContCap = 200, waterContImg = R.drawable.ic_glass_of_water_200ml),
        DrankWaterBase(waterContCap = 250, waterContImg = R.drawable.ic_glass_of_water_250ml),
        DrankWaterBase(waterContCap = 330, waterContImg = R.drawable.ic_can_soda_330ml),
        DrankWaterBase(waterContCap = 500, waterContImg = R.drawable.ic_water_bottle),
        DrankWaterBase(waterContCap = 750, waterContImg = R.drawable.ic_water_bottle_750ml),
        DrankWaterBase(waterContCap = 1000,  waterContImg = R.drawable.ic_water_bottle_1l)
    )
}