package com.mtszser.reminderapp.viewmodel



import android.util.Log
import androidx.lifecycle.*
import com.mtszser.reminderapp.model.*
import com.mtszser.reminderapp.repository.ActivityRepository
import com.mtszser.reminderapp.repository.DrankWaterRepository
import com.mtszser.reminderapp.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

import javax.inject.Inject
import kotlin.collections.ArrayList

sealed class WaterEvent{
    data class AddDeleteWater(val drankWaterView: DrankWaterView) : WaterEvent()
}

@HiltViewModel
class WaterViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val activityRepository: ActivityRepository,
    private val waterRepository: DrankWaterRepository
    ): ViewModel() {

    private val _waterUnitFlow = MutableSharedFlow<WaterEvent>()
    val waterUnitFlow = _waterUnitFlow as SharedFlow<WaterEvent>

    private val _waterState =  MutableLiveData(WaterStateData())
    val waterState = _waterState as LiveData<WaterStateData>


    init {
        loadDrankWaterList()
    }


    fun selectAmountOfWater(selectedAmountOfWater: DrankWaterBase) {
        _waterState.value = _waterState.value?.copy(
            selectedAmountOfWater = selectedAmountOfWater
        )
    }

    private fun loadDrankWaterList() {
        viewModelScope.launch {
            val alreadyDrank = userRepository.getWaterReminder().alreadyDrank
            val waterNeededPerDay = userRepository.getWaterReminder().waterContainer +
                    userRepository.getWaterReminder().bonusWaterContainer

            _waterState.value = waterState.value?.copy(
                drankWaterList = waterRepository.getAddedWater().map { it.mapToView()},
                drankWaterLabel = "$alreadyDrank / $waterNeededPerDay",
                alreadyDrank = alreadyDrank,
                waterPerDay = waterNeededPerDay

            )

        }
    }


    fun addWaterAmount() {
        _waterState.value?.selectedAmountOfWater?.let {
            viewModelScope.launch {
//                val waterNeededPerDay = userRepository.getWaterReminder().waterContainer +
//                        userRepository.getWaterReminder().bonusWaterContainer
                userRepository.addWater(it.waterContCap)
                waterRepository.insertWaterContainer(it)
                loadDrankWaterList()
            }
        }?: run {

        }

    }
    fun addDrankWaterActivity() {

    }

    fun clearWaterAmount() {
        viewModelScope.launch {

        }
    }
    fun deleteWaterAmount(drankWaterView: DrankWaterView) {
        viewModelScope.launch {
            waterRepository.deleteAddedWater(drankWaterView.mapToDatabase())
            userRepository.deleteWater(drankWaterView.mapToDatabase().waterContCap)
            loadDrankWaterList()
        }
    }


    fun passWaterUnitToFlow(drankWaterView: DrankWaterView) {
        viewModelScope.launch {
            _waterUnitFlow.emit(WaterEvent.AddDeleteWater(drankWaterView))
        }
    }


}

fun getDate(): String {
    val time = Calendar.getInstance().time
    val formatter = SimpleDateFormat("hh:mm a")
    return formatter.format(time)
}


data class WaterStateData(
    val selectedAmountOfWater: DrankWaterBase? = null,
    val drankWaterList: List<DrankWaterView> = listOf(),
    val drankWaterLabel: String = "",
    val alreadyDrank: Int = 0,
    val waterPerDay: Int = 0,
    )



/**
1. Create data class to hold screen water

WaterStateData

2. Create mutable live data to manage screen data

private val _waterState = MutableLiveData<WaterStateData>()
val waterState = _waterState as LiveData<WaterStateData>

3. To update data class use copy

_waterState.value = _waterState.value?.copy(selectedAmountOfWater = selectedAmountOfWater)

4. You implement if conditions in view model - not in fragment

5. Set default values in data class
 */




