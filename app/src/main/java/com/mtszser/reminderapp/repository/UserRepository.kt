package com.mtszser.reminderapp.repository

import com.mtszser.reminderapp.R
import com.mtszser.reminderapp.model.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
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

    // Water Repo Functions

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

    suspend fun addToWaterContainer(exerciseWaterIntake: Int) {
        userDao.addToWaterContainer(exerciseWaterIntake)
    }

    // Action Repo Functions

//    suspend fun getActionReminder(): List<ActionReminder>{
//        return userDao.getActionReminder()
//    }

    fun insertSpinnerData(): ArrayList<WaterContainers> {
        val containers = ArrayList<WaterContainers>()
        containers.apply {
            add(WaterContainers("0", "20", R.drawable.ic_soft_drink_straw_20ml))
            add(WaterContainers("1", "200", R.drawable.ic_glass_of_water_200ml))
            add(WaterContainers("2", "250", R.drawable.ic_glass_of_water_250ml))
            add(WaterContainers("3", "330", R.drawable.ic_can_soda_330ml))
            add(WaterContainers("4", "500", R.drawable.ic_water_bottle))
            add(WaterContainers("5", "750", R.drawable.ic_water_bottle_750ml))
            add(WaterContainers("6", "1000", R.drawable.ic_water_bottle_1l))
        }
        return containers
    }

    fun insertAutoCompletedText(): ArrayList<String> {
        val autoCompletedArray = ArrayList<String>()
        autoCompletedArray.apply {
            add(0, "Walking")
            add(1, "Medium Walking")
            add(2, "Intense Walking")
            add(3, "Jogging")
            add(4, "Running")
            add(5, "Riding a bike")
            add(6, "Roller skating")
            add(7, "Swimming")
            add(8, "Gym Workout")
            add(9, "Hot Day")
        }
        return autoCompletedArray
    }
    suspend fun insertExercises(exerciseBase: ExerciseBase){
        userDao.insertExercise(exerciseBase)
    }


    suspend fun saveSpinnerPos(spinnerPos: Int){
        userDao.saveSpinnerPos(spinnerPos)
    }

    suspend fun getContainerPos(): Int{
        return userDao.getContainerPos()
    }

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
