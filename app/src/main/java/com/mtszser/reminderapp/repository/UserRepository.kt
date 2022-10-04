package com.mtszser.reminderapp.repository

import androidx.lifecycle.LiveData
import com.mtszser.reminderapp.model.UserDao
import com.mtszser.reminderapp.model.UserProfile
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao) {

    suspend fun insert(userProfile: UserProfile) {
        userDao.insert(userProfile)
    }

    fun update(userProfile: UserProfile) {
        userDao.update(userProfile)
    }

    suspend fun getAll(): List<UserProfile> {
        return userDao.getAll()
    }



}