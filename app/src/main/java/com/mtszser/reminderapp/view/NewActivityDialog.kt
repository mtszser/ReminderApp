package com.mtszser.reminderapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatButton
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import com.mtszser.reminderapp.R
import com.mtszser.reminderapp.databinding.DialogActivityInputBinding
import com.mtszser.reminderapp.model.DrankWaterView
import com.mtszser.reminderapp.viewmodel.NewUserViewModel
import com.mtszser.reminderapp.viewmodel.WaterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewActivityDialog(): DialogFragment() {

    private lateinit var binding: DialogActivityInputBinding
    private val waterViewModel: WaterViewModel by viewModels()
    private lateinit var addButton: AppCompatButton
    private lateinit var cancelButton: AppCompatButton

    private val workoutDuration: String
        get() = binding.workoutDuration.text.toString()

    private val selectedExercise: String
        get() = binding.exerciseListAC.text.toString()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_FRAME, R.style.FullScreenDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogActivityInputBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//
////        prepopulateAC()
//        getButtons()
//    }
//
////    private fun prepopulateAC() {
////        val exercisesList = waterViewModel.getExerciseAC()
////        val exercisesAdapter = ArrayAdapter(requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
////        exercisesList)
////        binding.exerciseListAC.setAdapter(exercisesAdapter)
////    }
//
//    private fun getButtons() {
//        addButton = binding.addButton
//        cancelButton = binding.cancelButton
//
//        addButton.setOnClickListener { addActivity() }
//        cancelButton.setOnClickListener { dismiss() }
//    }
//
//    private fun addActivity() {
//        val duration = workoutDuration.toInt()
//        when (selectedExercise) {
//            "Walking" -> saveActivity(3, duration)
//            "Medium Walking" -> saveActivity(5, duration)
//            "Intense Walking" -> saveActivity(8, duration)
//            "Jogging" -> saveActivity(13, duration)
//            "Running" -> saveActivity(18, duration)
//            "Riding a bike" -> saveActivity(10, duration)
//            "Roller skating" -> saveActivity(10, duration)
//            "Swimming" -> saveActivity(10, duration)
//            "Gym Workout" -> saveActivity(5, duration)
//            "Hot Day" -> saveActivity(500, 1)
//        }
//    }
//
//    private fun saveActivity(waterPerMinute: Int, duration: Int) {
//        val waterIntake = waterViewModel.countWaterDuringExercise(duration, waterPerMinute)
//        setFragmentResult("activityResult", bundleOf("waterIntake" to waterIntake, "selectedItem" to selectedExercise))
//        dismiss()
//        }

    }
}

