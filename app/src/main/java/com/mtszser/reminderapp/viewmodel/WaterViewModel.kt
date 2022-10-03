package com.mtszser.reminderapp.viewmodel


import android.util.Log
import androidx.lifecycle.*
import androidx.loader.content.Loader
import com.mtszser.reminderapp.model.UserProfile
import com.mtszser.reminderapp.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

import javax.inject.Inject


@HiltViewModel
class WaterViewModel @Inject constructor(private val repo: UserRepository): ViewModel() {

    private val _allUsers = MutableLiveData<List<UserProfile>>()
    val allUsers = _allUsers as LiveData<List<UserProfile>>
    private val _stateOfWater = MutableLiveData<StateOfWater>()
    val stateOfWater = _stateOfWater as LiveData<StateOfWater>


    init {
        viewModelScope.launch {
            _stateOfWater.value = StateOfWater.Loaded(
                userList = repo.getAll(),
            )
        }

    }



    fun countWaterIntake() {
//        val list: List<UserProfile>? = allUsers.value
//        list?.forEach { UserProfile ->
//            _stateOfWater.value = _stateOfWater.value?.copy(
//                waterContainer = UserProfile.height,
//                waterIntake = UserProfile.weight,
//                userList = list
//            )
//        }
    }

    fun addWater() {

    }

    sealed class StateOfWater{
    data class Loaded(
        val userList: List<UserProfile> = listOf(),
        val waterContainer: String = "",
        val waterIntake: String = "",
    ): StateOfWater()
        object Error: StateOfWater()
        object Loading: StateOfWater()
    }

}

