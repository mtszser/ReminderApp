package com.mtszser.reminderapp.viewmodel



import androidx.lifecycle.*
import com.mtszser.reminderapp.model.*
import com.mtszser.reminderapp.repository.ActivityRepository
import com.mtszser.reminderapp.repository.DrankWaterRepository
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
class WaterViewModel @Inject constructor(private val repo: UserRepository, private val activityRepo: ActivityRepository, private val waterRepo: DrankWaterRepository): ViewModel() {

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
                activityList = activityRepo.getAllActivities(),
                listOfWaterContainers = waterRepo.getAddedWater()
            )
//            try {
//                _position.value = repo.getContainerPos()
//                _stateOfWater.value = StateOfWater.Loaded(
//                    countWaterList = repo.getWaterReminder(),
//                )
//            } catch (e: Exception) {
//                _stateOfWater.postValue(StateOfWater.Error)
//            }
            _stateOfWater.value = StateOfWater.Loaded2(
                userProfile = repo.getAll()
            )
        }

    }



    fun updateSpinner(): ArrayList<DrankWaterBase> {
        return waterRepo.insertSpinnerData()
    }

    fun getExerciseAC(): ArrayList<String> {
        return repo.insertAutoCompletedText()
    }

    fun insertExercise(exerciseBase: ExerciseBase) = viewModelScope.launch(Dispatchers.IO) {
       activityRepo.insertExercises(exerciseBase)
        _stateOfWater.postValue(StateOfWater.Loaded(
            activityList = activityRepo.getAllActivities(),
            alreadyDrank = repo.getWaterReminder().alreadyDrank
        ))
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
            bonusWaterContainer = 0,
            countWaterList = repo.getWaterReminder()

        )
        updateWater()
    }

    fun deleteAddedWater(drunkWaterBase: DrankWaterBase) = viewModelScope.launch(Dispatchers.IO) {
        waterRepo.deleteAddedWater(drunkWaterBase)
    }


    fun addToBonusWaterContainer(exerciseWaterIntake: Int) = viewModelScope.launch(){
        repo.addToBonusWaterContainer(exerciseWaterIntake)
        _stateOfWater.postValue(StateOfWater.Loaded(
            bonusWaterContainer = repo.getWaterReminder().bonusWaterContainer,
            alreadyDrank = repo.getWaterReminder().alreadyDrank,
            countWaterList = repo.getWaterReminder(),
            waterContainer = repo.getWaterReminder().waterContainer + repo.getWaterReminder().bonusWaterContainer,

        ))
    }


    fun updateWater() = viewModelScope.launch() {
        _stateOfWater.value = StateOfWater.Loaded(
            countWaterList = repo.getWaterReminder(),
            listOfWaterContainers = waterRepo.getAddedWater()
        )
    }


    fun saveSpinnerPos(spinnerPos: Int) = viewModelScope.launch(Dispatchers.IO){
        repo.saveSpinnerPos(spinnerPos)
    }

    fun getTime(): String {
        return waterRepo.getDate()
    }

    fun compareDates() = viewModelScope.launch(Dispatchers.IO) {
        val today = repo.getDate()
        val profileDate = repo.getProfileDate()
        val formatter = SimpleDateFormat("yyyy-MM-dd")
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

    fun countWaterDuringExercise(duration: Int, waterPerMinute: Int): Int {
        return duration * waterPerMinute
    }

    fun countEntireWaterContainer(waterContainer: Int, bonusWaterContainer: Int): Int {
        return waterContainer + bonusWaterContainer
    }




    fun addWater(drunkWater: Int, containers: DrankWaterBase, time: String) = viewModelScope.launch(Dispatchers.IO) {
        repo.addWater(drunkWater)
        containers.addedDate = time
        waterRepo.insertWaterContainer(containers)
        _stateOfWater.postValue(StateOfWater.Loaded(
            alreadyDrank = repo.getWaterReminder().alreadyDrank,
            waterContainer = repo.getWaterReminder().waterContainer,
            countWaterList = repo.getWaterReminder(),
            listOfWaterContainers = waterRepo.getAddedWater()
        ))

    }

    fun deleteWater(drunkWater: Int) = viewModelScope.launch(Dispatchers.IO) {
        repo.deleteWater(drunkWater)
        _stateOfWater.postValue((StateOfWater.Loaded(
            alreadyDrank = repo.getWaterReminder().alreadyDrank,
            waterContainer = repo.getWaterReminder().waterContainer,
            countWaterList = repo.getWaterReminder(),
            listOfWaterContainers = waterRepo.getAddedWater()
        )))
    }

    fun resetDrankWater() = viewModelScope.launch(Dispatchers.IO) {
        repo.resetDrankWater()
        _stateOfWater.postValue(StateOfWater.Loaded(
            alreadyDrank = 0,
            waterContainer = repo.getWaterReminder().waterContainer + repo.getWaterReminder().bonusWaterContainer
        ))
    }




    sealed class StateOfWater{
        data class Loaded(
            val alreadyDrank: Int = Integer.MIN_VALUE + 0,
            val waterContainer: Int = 0,
            val bonusWaterContainer: Int = 0,
            val currentDate: String = "",
            val listOfWaterContainers: List<DrankWaterBase> = listOf(),
            val activityList: List<ExerciseBase> = listOf(),
            val countWaterList: WaterReminder? = WaterReminder(0, waterContainer = waterContainer, alreadyDrank = alreadyDrank, currentDate = currentDate,
            bonusWaterContainer = bonusWaterContainer),
        ): StateOfWater()
        data class Loaded2(
            val userProfile: List<UserProfile>? = listOf(),
        ): StateOfWater()
        object Error: StateOfWater()
        object Loading: StateOfWater()
        object IsEmpty: StateOfWater()
    }
}

