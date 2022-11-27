package com.mtszser.reminderapp.viewmodel

import android.media.metrics.Event
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mtszser.reminderapp.model.*
import com.mtszser.reminderapp.repository.ActivityRepository
import com.mtszser.reminderapp.repository.DrankWaterRepository
import com.mtszser.reminderapp.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor(
    private val waterRepository: DrankWaterRepository,
    private val userRepository: UserRepository,
    private val activityRepository: ActivityRepository
): ViewModel() {

    private val _exerciseState = MutableLiveData(ExerciseStateData())
    val exerciseState = _exerciseState as LiveData<ExerciseStateData>

    private val _exerciseValidationFlow = MutableSharedFlow<ExerciseValidationEvent>()
    val exerciseValidationFlow = _exerciseValidationFlow as SharedFlow<ExerciseValidationEvent>

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            _exerciseState.value = _exerciseState.value?.copy(
                baseExerciseList = activityRepository.getAllActivities().map { it.mapToView() }
            )
        }
    }

    fun selectActivity(selectedExercise: ExerciseBase) {
        _exerciseState.value = _exerciseState.value?.copy(
            selectedExercise = selectedExercise
        )
    }

    fun addActivity(exerciseDuration: Int) {

            _exerciseState.value?.selectedExercise?.let {
                viewModelScope.launch {
                    val bonusWaterConsumption = countBonusCalories(it.bonusActivityWater, exerciseDuration)
                    userRepository.addToBonusWaterContainer(bonusWaterConsumption)
                    activityRepository.insertExercises(it)
                }
            }
    }

    private fun countBonusCalories(exerciseWaterExpenditure: Int, exerciseDuration: Int): Int {
        return exerciseWaterExpenditure * exerciseDuration
    }

    fun onWorkoutDurationFocusChange(workoutDurationValue: String) {
        viewModelScope.launch {
            if (workoutDurationValue.isEmpty()){
                _exerciseValidationFlow.emit(ExerciseValidationEvent.IsEmpty)
            } else if(workoutDurationValue.isNotEmpty() && workoutDurationValue.toInt() in 1..300){
                _exerciseValidationFlow.emit(ExerciseValidationEvent.IsValidated)
            } else if(workoutDurationValue.toInt() !in 1..300){
                _exerciseValidationFlow.emit(ExerciseValidationEvent.DurationOutOfRange)
            } else if(workoutDurationValue.toInt() < 0) {
                _exerciseValidationFlow.emit(ExerciseValidationEvent.DurationValueIsNegative)
            }

        }
    }




}

data class ExerciseStateData(
    val selectedExercise: ExerciseBase? = null,
    val exerciseDuration: Int = 0,
    val baseExerciseList: List<ExerciseBaseView> = listOf()
)

sealed class ExerciseValidationEvent{
    object IsEmpty: ExerciseValidationEvent()
    object IsValidated: ExerciseValidationEvent()
    object DurationOutOfRange: ExerciseValidationEvent()
    object DurationValueIsNegative: ExerciseValidationEvent()
}
