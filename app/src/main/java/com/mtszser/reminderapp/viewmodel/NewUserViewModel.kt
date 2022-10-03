package com.mtszser.reminderapp.viewmodel

import android.app.Application
import android.util.Log
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

    private val _allUsers = MutableLiveData<List<UserProfile>>()
    val allUsers = _allUsers as LiveData<List<UserProfile>>
    private val _state = MutableLiveData<State>()
    val state = _state as LiveData<State>

    init {
        viewModelScope.launch {
            _allUsers.value = repo.getAll()
            _state.value = State()

        }
    }

    fun startApp() {
        val list: List<UserProfile>? = allUsers.value
        Log.d("lista", "$list")
        list?.forEach { userProfile -> _state.value = _state.value?.copy(
            name = userProfile.firstName,
            weight = userProfile.weight,
            height = userProfile.height,
            userList = list
        ) }



    }

    fun insert(userProfile: UserProfile) = viewModelScope.launch(Dispatchers.IO) {
        repo.insert(userProfile)
    }
    fun update(userProfile: UserProfile) = viewModelScope.launch(Dispatchers.IO) {
        repo.update(userProfile)
    }




    data class State(
        val name: String? = "",
        val weight: String? = "",
        val height: String? = "",
        val userList: List<UserProfile>? = listOf()


    )



}