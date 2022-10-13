package com.mtszser.reminderapp.repository

import com.mtszser.reminderapp.R
import com.mtszser.reminderapp.model.*
import javax.inject.Inject

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

    // Water Repo Functions

    suspend fun getWaterReminder(): WaterReminder{
        return userDao.getWaterReminder()
    }

    suspend fun updateWater(drunkWater: Int){
        userDao.updateWater(drunkWater = drunkWater)
    }

    suspend fun resetWater(){
        userDao.resetWater()
    }

    // Action Repo Functions

//    suspend fun getActionReminder(): List<ActionReminder>{
//        return userDao.getActionReminder()
//    }

    fun insertSpinnerData(): ArrayList<WaterContainers> {
        val containers = ArrayList<WaterContainers>()
        containers.apply {
            add(WaterContainers("0", "200", R.drawable.ic_glass_of_water_200ml))
            add(WaterContainers("1", "250", R.drawable.ic_glass_of_water_250ml))
            add(WaterContainers("2", "330", R.drawable.ic_can_soda_330ml))
            add(WaterContainers("3", "500", R.drawable.ic_water_bottle))
            add(WaterContainers("4", "750", R.drawable.ic_water_bottle_750ml))
            add(WaterContainers("5", "1000", R.drawable.ic_water_bottle_1l))
        }
        return containers
    }

    suspend fun saveSpinnerPos(spinnerPos: Int){
        userDao.saveSpinnerPos(spinnerPos)
    }

    suspend fun getContainerPos(): Int{
        return userDao.getContainerPos()
    }
}
