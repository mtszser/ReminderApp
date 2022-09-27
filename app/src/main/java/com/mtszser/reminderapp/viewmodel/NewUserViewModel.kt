package com.mtszser.reminderapp.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.mtszser.reminderapp.model.UserDatabase

import com.mtszser.reminderapp.model.UserProfile
import com.mtszser.reminderapp.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewUserViewModel @Inject constructor(private val repo: UserRepository): ViewModel() {

    private var allUsers: LiveData<List<UserProfile>> = repo.allUsers


    fun insert(userProfile: UserProfile) = viewModelScope.launch(Dispatchers.IO) {
        repo.insert(userProfile)
    }

    fun update(userProfile: UserProfile) = viewModelScope.launch(Dispatchers.IO) {
        repo.update(userProfile)
    }

    fun getAll(): LiveData<List<UserProfile>> {
        return allUsers
    }



}