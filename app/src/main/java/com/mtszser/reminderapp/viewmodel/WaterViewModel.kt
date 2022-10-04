package com.mtszser.reminderapp.viewmodel


import android.util.Log
import androidx.lifecycle.*
import com.mtszser.reminderapp.model.UserProfile
import com.mtszser.reminderapp.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel

import javax.inject.Inject


@HiltViewModel
class WaterViewModel @Inject constructor(private val repo: UserRepository): ViewModel() {

    private val _allUsers = MutableLiveData<List<UserProfile>>()
    val allUsers = _allUsers as LiveData<List<UserProfile>>
    private val _stateOfWater = MutableLiveData<StateOfWater>()
    val stateOfWater = _stateOfWater as LiveData<StateOfWater>



    init {
        _stateOfWater.value = StateOfWater()

    }



    fun countWaterIntake() {

    }

    fun addWater() {

    }


    data class StateOfWater(
        val waterContainer: String? = "100",
        val waterIntake: String? = "Zbita",
        )
}

