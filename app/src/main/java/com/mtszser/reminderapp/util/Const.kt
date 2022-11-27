package com.mtszser.reminderapp.util

import com.mtszser.reminderapp.R
import com.mtszser.reminderapp.model.BaseActivities
import com.mtszser.reminderapp.model.DrankWaterBase
import com.mtszser.reminderapp.model.ExerciseBase

object Const {
    fun insertSpinnerData(): ArrayList<DrankWaterBase> = arrayListOf(
        DrankWaterBase(waterContCap = 20, waterContImg = R.drawable.ic_soft_drink_straw_20ml),
        DrankWaterBase(waterContCap = 200, waterContImg = R.drawable.ic_glass_of_water_200ml),
        DrankWaterBase(waterContCap = 250, waterContImg = R.drawable.ic_glass_of_water_250ml),
        DrankWaterBase(waterContCap = 330, waterContImg = R.drawable.ic_can_soda_330ml),
        DrankWaterBase(waterContCap = 500, waterContImg = R.drawable.ic_water_bottle),
        DrankWaterBase(waterContCap = 750, waterContImg = R.drawable.ic_water_bottle_750ml),
        DrankWaterBase(waterContCap = 1000,  waterContImg = R.drawable.ic_water_bottle_1l)
    )

    fun insertActivitySpinnerData(): ArrayList<BaseActivities> = arrayListOf(
        BaseActivities(baseActivityID = 1, baseActivityName = "Sedentary", baseActivityDesc = "does little or no physical movement and or exercise.", baseActivityBonusCalories = 300),
        BaseActivities(baseActivityID = 2, baseActivityName = "Light Activity", baseActivityDesc = "basic activities like 30 min walking per day, jogging or physical work", baseActivityBonusCalories = 500),
        BaseActivities(baseActivityID = 3, baseActivityName = "Moderately Active", baseActivityDesc = "1-3 workouts per week, running, hard physical work", baseActivityBonusCalories = 800),
        BaseActivities(baseActivityID = 4, baseActivityName = "Very Active", baseActivityDesc = "at least 4-6 workouts per week, warm climate, hard physical work etc. ", baseActivityBonusCalories = 1300),
    )

    fun insertExerciseSpinnerData(): ArrayList<ExerciseBase> = arrayListOf(
        ExerciseBase(activityName = "Walking", bonusActivityWater = 0, activityImage = R.drawable.ic_walking),
        ExerciseBase(activityName = "Medium Walking", bonusActivityWater = 0, activityImage = R.drawable.ic_walking),
        ExerciseBase(activityName = "Intense Walking", bonusActivityWater = 0, activityImage = R.drawable.ic_walking),
        ExerciseBase(activityName = "Jogging", bonusActivityWater = 0, activityImage = R.drawable.ic_running),
        ExerciseBase(activityName = "Running", bonusActivityWater = 0, activityImage = R.drawable.ic_running),
        ExerciseBase(activityName = "Roller skating", bonusActivityWater = 0, activityImage = R.drawable.ic_roller_skates),
        ExerciseBase(activityName = "Swimming", bonusActivityWater = 0, activityImage = R.drawable.ic_swimming),
        ExerciseBase(activityName = "Gym Workout", bonusActivityWater = 0, activityImage = R.drawable.ic_gym_weight_svgrepo_com),
        ExerciseBase(activityName = "Hot day", bonusActivityWater = 500, activityImage = R.drawable.ic_hot_day),
    )
}
