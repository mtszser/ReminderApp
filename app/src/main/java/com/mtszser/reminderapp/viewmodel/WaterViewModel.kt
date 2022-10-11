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

import javax.inject.Inject


@HiltViewModel
class WaterViewModel @Inject constructor(private val repo: UserRepository): ViewModel() {

    private val isNewDay = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
    private val _allUsers = MutableLiveData<WaterReminder>()
    private val _position = MutableLiveData<Int>()
    val position = _position as LiveData<Int>
    val allUsers = _allUsers as LiveData<WaterReminder>
    private val _stateOfWater = MutableLiveData<StateOfWater>()
    val stateOfWater = _stateOfWater as LiveData<StateOfWater>



    init {
        viewModelScope.launch {
            _position.value = repo.getContainerPos()
            _allUsers.value = repo.getWaterReminder()
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

    fun updateSpinnerPosition(position: Int) {
        _position.value = position
    }

    fun getPosition() = viewModelScope.launch(Dispatchers.IO){
        repo.getContainerPos()
    }



    fun updateWater() = viewModelScope.launch() {
        _stateOfWater.value = StateOfWater.Loaded(
            countWaterList = repo.getWaterReminder(),
        )
    }

    fun saveSpinnerPos(spinnerPos: Int) = viewModelScope.launch(Dispatchers.IO){
        repo.saveSpinnerPos(spinnerPos)
    }


    fun compareDays(): Boolean {
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        return false
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
            val countWaterList: WaterReminder? = WaterReminder(0, waterContainer = waterContainer, alreadyDrank = alreadyDrank,),
        ): StateOfWater()
        data class Loaded2(
            val userProfile: List<UserProfile>? = listOf(),
        ): StateOfWater()
        object Error: StateOfWater()
        object Loading: StateOfWater()
        object IsEmpty: StateOfWater()
    }
}

