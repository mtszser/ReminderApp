package com.mtszser.reminderapp.repository

import androidx.lifecycle.LiveData
import com.mtszser.reminderapp.model.UserDao
import com.mtszser.reminderapp.model.UserProfile
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao) {

    val allUsers: LiveData<List<UserProfile>> = userDao.getAll()

    fun insert(userProfile: UserProfile) {
        userDao.insert(userProfile)
    }

    fun update(userProfile: UserProfile) {
        userDao.update(userProfile)
    }

    fun getAll(): LiveData<List<UserProfile>> {
        return userDao.getAll()
    }



}