package com.mtszser.reminderapp.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DrankWaterDao {

    @Query("Select * from water_table")
    suspend fun getAddedWater(): List<DrankWaterBase>

    @Insert
    suspend fun insertWaterContainer(waterContainer: DrankWaterBase)

    @Delete
    suspend fun deleteAddedWater(drankWaterBase: DrankWaterBase)
}