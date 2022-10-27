package com.mtszser.reminderapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mtszser.reminderapp.R

@Entity(tableName = "water_table")
data class DrankWaterBase(
    @PrimaryKey(autoGenerate = true) val waterContID: Int,
    @ColumnInfo val waterContCap: Int,
    @ColumnInfo val waterContImg: Int,
    @ColumnInfo var addedDate: String,
) {

    fun mapDataClass() = DrankWaterView(
        dwID = waterContID,
        dwCap = waterContCap,
        dwImg = waterContImg,
        dwDate = addedDate
    )
}

