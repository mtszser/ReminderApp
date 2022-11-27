package com.mtszser.reminderapp.view.adapters

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.ImageView
import android.widget.TextView
import com.mtszser.reminderapp.R
import com.mtszser.reminderapp.databinding.BaseActivitiesSpinnerLayoutBinding
import com.mtszser.reminderapp.model.ExerciseBase
import com.mtszser.reminderapp.util.Const

class ExercisesSpinnerAdapter(context: Context): ArrayAdapter<ExerciseBase>(context,0, Const.insertExerciseSpinnerData()) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return this.createView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return this.createView(position, convertView, parent)
    }

    private fun createView(position: Int, convertView: View?, parent: ViewGroup): View {

        val exerciseBaseItem = getItem(position)
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.base_exercises_spinner_layout, parent, false)
        val baseExerciseName = view.findViewById<TextView>(R.id.baseExerciseName)
        val baseExerciseImage = view.findViewById<ImageView>(R.id.baseExerciseImage)
        baseExerciseImage.setImageResource(exerciseBaseItem!!.activityImage)
        baseExerciseName.text = exerciseBaseItem.activityName

        return view
    }




}
