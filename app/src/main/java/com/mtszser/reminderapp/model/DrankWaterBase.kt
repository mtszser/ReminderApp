package com.mtszser.reminderapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mtszser.reminderapp.R
import com.mtszser.reminderapp.viewmodel.getDate

@Entity(tableName = "water_table")
    data class DrankWaterBase(
    @PrimaryKey(autoGenerate = true) val waterContID: Int = 0,
    @ColumnInfo val waterContCap: Int = 0,
    @ColumnInfo val waterContImg: Int = 0,
    @ColumnInfo var addedDate: String = getDate(),
)

fun DrankWaterBase.mapToView() = DrankWaterView(
    dwID = this.waterContID,
    dwCap = this.waterContCap,
    dwImg = this.waterContImg,
    dwDate = this.addedDate
)


