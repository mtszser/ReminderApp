package com.mtszser.reminderapp.model

import androidx.room.Entity

@Entity(tableName = "action_table")
data class ItemData(val actionName: String?, val actionDate: String?, val actionTime: String?)
