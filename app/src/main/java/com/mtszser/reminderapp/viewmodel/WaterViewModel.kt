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


//    private val _items = MutableLiveData<List<WaterData>>()
//    val items = _items as LiveData<List<WaterData>>
//    private val _itemsLoadError = MutableLiveData<Boolean>()
//    val itemsLoadError = _itemsLoadError as LiveData<Boolean>
//    private val _itemsLoading = MutableLiveData<Boolean>()
//    val itemsLoading = _itemsLoading as LiveData<Boolean>

//    private fun MutableLiveData<List<WaterData>>.asLiveData() = this as LiveData<List<WaterData>>


    init {
        _stateOfWater.value = StateOfWater()

    }

//    fun refreshWater() {
//        fetchWater()
//
//    }

//    private fun fetchWater() {
//        _itemsLoading.value = true
//
//
//        val itemList = listOf(
//            WaterData("2500", "0"),
//        )
//
//        _itemsLoadError.value = false
//        _itemsLoading.value = false
//        _items.value = itemList
//
//
//    }


    fun countWaterIntake() {
        _allUsers.value = repo.allUsers.value
        Log.i("getProfile", "$_allUsers")

//        _allUsers.value = repo.allUsers.value
//        val list: List<UserProfile>? = allUsers.value
//        Log.i("userInformation", "${repo.getAll().value}")
//        list?.forEach { userProfile ->
//            _stateOfWater.value = _stateOfWater.value?.copy(
//                waterContainer = userProfile.weight,
//                waterIntake = userProfile.height
//            )
//        }
    }

    fun addWater() {

    }


    data class StateOfWater(
        val waterContainer: String? = "",
        val waterIntake: String? = "",
        )
}

