package com.mtszser.reminderapp.viewmodel



import android.util.Log
import androidx.lifecycle.*
import com.mtszser.reminderapp.model.*
import com.mtszser.reminderapp.repository.ActivityRepository
import com.mtszser.reminderapp.repository.DrankWaterRepository
import com.mtszser.reminderapp.repository.UserRepository
import com.mtszser.reminderapp.view.NewActivityDialog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

import javax.inject.Inject
import kotlin.collections.ArrayList


@HiltViewModel
class WaterViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val activityRepository: ActivityRepository,
    private val waterRepository: DrankWaterRepository
    ): ViewModel() {

    private val _unitFlow = MutableSharedFlow<Unit>()
    val unitFlow = _unitFlow as SharedFlow<Unit>

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
                drankWaterLabel = "$alreadyDrank / $waterNeededPerDay"
            )
        }
    }


    fun addWaterAmount() {
        _waterState.value?.selectedAmountOfWater?.let {
            viewModelScope.launch {
                val waterNeededPerDay = userRepository.getWaterReminder().waterContainer +
                        userRepository.getWaterReminder().bonusWaterContainer
                userRepository.addWater(it.waterContCap)
                waterRepository.insertWaterContainer(it)

                val waterValue = userRepository.getWaterReminder().alreadyDrank

                _waterState.value = _waterState.value?.copy(
                    drankWaterList = waterRepository.getAddedWater().map { it.mapToView()}.reversed(),
                    drankWaterLabel = "$waterValue / $waterNeededPerDay"
                )

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
    fun deleteWaterAmount() {
        viewModelScope.launch {

        }
    }

    fun selectDrankWaterItem(drankWaterView: DrankWaterView) {
        viewModelScope.launch {
          Log.d("sasdas", "${drankWaterView.dwCap}")
        }
    }

    fun passUnitToFlow() {
        viewModelScope.launch {
            _unitFlow.emit(Unit)
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




