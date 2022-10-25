package com.mtszser.reminderapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "water_table")
class DrankWaterBase(
    @PrimaryKey(autoGenerate = true) val waterContID: Int,
    @ColumnInfo val waterContCap: Int,
    @ColumnInfo val waterContImg: Int,
)
