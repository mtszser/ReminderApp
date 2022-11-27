package com.mtszser.reminderapp.repository

import com.mtszser.reminderapp.model.ActivityDao
import com.mtszser.reminderapp.model.ExerciseBase
import com.mtszser.reminderapp.model.ExerciseBaseView
import javax.inject.Inject

class ActivityRepository @Inject constructor(private val activityDao: ActivityDao) {

    suspend fun getAllActivities(): List<ExerciseBase> {
        return activityDao.getAllActivities()
    }

    suspend fun insertExercises(exerciseBase: ExerciseBase){
        activityDao.insertExercise(exerciseBase)
    }

    suspend fun getActivity(): ExerciseBase {
        return activityDao.getActivity()
    }

}