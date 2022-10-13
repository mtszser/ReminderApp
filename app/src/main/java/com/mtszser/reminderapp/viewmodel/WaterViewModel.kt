package com.mtszser.reminderapp.viewmodel



import androidx.lifecycle.*
import com.mtszser.reminderapp.model.UserProfile
import com.mtszser.reminderapp.model.WaterContainers
import com.mtszser.reminderapp.model.WaterReminder
import com.mtszser.reminderapp.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

import javax.inject.Inject
import kotlin.collections.ArrayList


@HiltViewModel
class WaterViewModel @Inject constructor(private val repo: UserRepository): ViewModel() {

    private val _currentDate = MutableLiveData<String>()
    val currentDate = _currentDate as LiveData<String>
    private val _position = MutableLiveData<Int>()
    val position = _position as LiveData<Int>
    private val _stateOfWater = MutableLiveData<StateOfWater>()
    val stateOfWater = _stateOfWater as LiveData<StateOfWater>



    init {
        viewModelScope.launch {
            _position.value = repo.getContainerPos()
            _stateOfWater.value = StateOfWater.Loaded(
                countWaterList = repo.getWaterReminder(),
            )
            _stateOfWater.value = StateOfWater.Loaded2(
                userProfile = repo.getAll()
            )
        }

    }

    fun updateSpinner(): ArrayList<WaterContainers> {
        return repo.insertSpinnerData()
    }

    fun updateSpinnerPosition(position: Int) = viewModelScope.launch() {
        _position.value = position
    }

    fun getPosition() = viewModelScope.launch(){
        _position.value = repo.getContainerPos()
    }

    fun resetCap() = viewModelScope.launch {
        repo.resetWater()
        _stateOfWater.value = StateOfWater.Loaded(
            alreadyDrank = 0,
        )
        updateWater()
    }


    fun updateWater() = viewModelScope.launch() {
        _stateOfWater.value = StateOfWater.Loaded(
            countWaterList = repo.getWaterReminder(),
        )
    }

    fun saveSpinnerPos(spinnerPos: Int) = viewModelScope.launch(Dispatchers.IO){
        repo.saveSpinnerPos(spinnerPos)
    }

    fun compareDates() = viewModelScope.launch(Dispatchers.IO) {
        val today = repo.getDate()
        val profileDate = repo.getProfileDate()
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm")
        val firstDate: Date = formatter.parse(today)
        val secondDate: Date = formatter.parse(profileDate)
        when {
            firstDate.after(secondDate) -> {
                updateDate(today)
                resetCap()
            }
            firstDate.before(secondDate) -> {
                //wtf happened here :D
            }
            firstDate == secondDate -> {
                // its fine do nothing
            }
        }


    }


    private fun updateDate(currentDate: String) = viewModelScope.launch(Dispatchers.IO) {
        repo.updateDate(currentDate)
    }




    fun addWater(drunkWater: Int) = viewModelScope.launch(Dispatchers.IO) {
        repo.updateWater(drunkWater)
        _stateOfWater.postValue(StateOfWater.Loaded(
            alreadyDrank = repo.getWaterReminder().alreadyDrank,
            waterContainer = repo.getWaterReminder().waterContainer,
            countWaterList = repo.getWaterReminder())
        )

    }




    sealed class StateOfWater{
        data class Loaded(
            val alreadyDrank: Int = 0,
            val waterContainer: Int = 0,
            val currentDate: String = "",
            val countWaterList: WaterReminder? = WaterReminder(0, waterContainer = waterContainer, alreadyDrank = alreadyDrank, currentDate = currentDate ),
        ): StateOfWater()
        data class Loaded2(
            val userProfile: List<UserProfile>? = listOf(),
        ): StateOfWater()
        object Error: StateOfWater()
        object Loading: StateOfWater()
        object IsEmpty: StateOfWater()
    }
}

