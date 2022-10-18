package com.mtszser.reminderapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "action_table" )
data class ActionReminder(
    @PrimaryKey(autoGenerate = true)val actionID: Int,
    @ColumnInfo val actionName: String,
    @ColumnInfo val actionDesc: String,
)
