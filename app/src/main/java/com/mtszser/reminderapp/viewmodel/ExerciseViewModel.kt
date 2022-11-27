package com.mtszser.reminderapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mtszser.reminderapp.model.BaseActivities
import com.mtszser.reminderapp.model.DrankWaterBase
import com.mtszser.reminderapp.model.ExerciseBase
import com.mtszser.reminderapp.model.ExerciseBaseView
import com.mtszser.reminderapp.repository.DrankWaterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor(
    private val waterRepository: DrankWaterRepository
): ViewModel() {

    private val _exerciseState = MutableLiveData(ExerciseStateData())
    val exerciseState = _exerciseState as LiveData<ExerciseStateData>

}

data class ExerciseStateData(
    val selectedExercise: ExerciseBase? = null,
    val exerciseDuration: Int = 0,
    val baseExerciseList: List<ExerciseBaseView> = listOf()

)