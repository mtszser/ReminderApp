package com.mtszser.reminderapp.repository

import com.mtszser.reminderapp.R
import com.mtszser.reminderapp.model.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class UserRepository @Inject constructor(private val userDao: UserDao) {

    suspend fun insert(userProfile: UserProfile) {
        userDao.insert(userProfile)
    }

    suspend fun update(userProfile: UserProfile) {
        userDao.update(userProfile)
    }

    suspend fun delete(userProfile: UserProfile) {
        userDao.delete(userProfile)
    }

    suspend fun getAll(): List<UserProfile> {
        return userDao.getAll()
    }

    suspend fun getUserProfile(): UserProfile {
        return userDao.getUserProfile()
    }


    suspend fun getWaterReminder(): WaterReminder{
        return userDao.getWaterReminder()
    }

    suspend fun addWater(drunkWater: Int){
        userDao.addWater(drunkWater = drunkWater)
    }

    suspend fun deleteWater(drunkWater: Int){
        userDao.deleteWater(drunkWater = drunkWater)
    }

    suspend fun resetWater(){
        userDao.resetWater()
    }

    suspend fun resetDrankWater(){
        userDao.resetDrankWater()
    }


    suspend fun addToBonusWaterContainer(exerciseWaterIntake: Int) {
        userDao.addToBonusWaterContainer(exerciseWaterIntake)
    }

    // Action Repo Functions

//    suspend fun getActionReminder(): List<ActionReminder>{
//        return userDao.getActionReminder()
//    }



    fun getDate(): String {
        val time = Calendar.getInstance().time
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        return formatter.format(time)
    }

    suspend fun updateDate(currentDate: String){
        return userDao.updateDate(currentDate)
    }

    suspend fun getProfileDate(): String {
        return userDao.getProfileDate()
    }

}
