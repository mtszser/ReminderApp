package com.mtszser.reminderapp.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DrankWaterDao {

    @Query("Select * from water_table")
    suspend fun getAllWater(): List<DrankWaterBase>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWaterContainer(waterContainer: DrankWaterBase)

}