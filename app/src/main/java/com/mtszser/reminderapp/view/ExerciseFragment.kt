package com.mtszser.reminderapp.view

import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AutoCompleteTextView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.mtszser.reminderapp.R
import com.mtszser.reminderapp.databinding.FragmentExerciseBinding
import com.mtszser.reminderapp.model.DrankWaterBase
import com.mtszser.reminderapp.model.ExerciseBase
import com.mtszser.reminderapp.util.Const
import com.mtszser.reminderapp.view.adapters.ExercisesSpinnerAdapter
import com.mtszser.reminderapp.viewmodel.ExerciseValidationEvent
import com.mtszser.reminderapp.viewmodel.ExerciseViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.zip.Inflater

@AndroidEntryPoint
class ExerciseFragment : Fragment() {

    private lateinit var binding: FragmentExerciseBinding
    private val exerciseViewModel: ExerciseViewModel by viewModels()

    private val workoutDuration
        get() = binding.workoutDuration

    private val workoutDurationLayout
    get() = binding.workoutDurationLayout

    private val exerciseACTV
        get() = binding.exerciseACTV

    private val backButton: AppCompatButton
    get() = binding.backButton

    private val addActivityButton: AppCompatButton
        get() = binding.addActivityButton


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExerciseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inputValidation()
        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                exerciseViewModel.exerciseValidationFlow.collect { event ->
                    when(event) {
                        ExerciseValidationEvent.IsEmpty -> {
                            workoutDurationLayout.error = getString(R.string.value_is_empty)
                        }
                        ExerciseValidationEvent.IsValidated -> {
                            workoutDurationLayout.error = null
                        }
                        ExerciseValidationEvent.DurationOutOfRange -> {
                            workoutDurationLayout.error = getString(R.string.value_out_of_range)
                        }
                        ExerciseValidationEvent.DurationValueIsNegative -> {
                            workoutDurationLayout.error = getString(R.string.value_cannot_be_negative)
                        }
                        else -> {
                            error("Something bad happened")}
                    }
                    }
                }
            }

        backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        exerciseACTV.apply {
            setAdapter(ExercisesSpinnerAdapter(requireContext()))
            setOnItemClickListener { _, _, position, _ ->
                val getExerciseItem = adapter.getItem(position) as ExerciseBase
                exerciseViewModel.selectActivity(getExerciseItem)
                addActivity()

            }

        }


    }

    private fun inputValidation() {
        workoutDuration.setOnFocusChangeListener{_, _ ->
            exerciseViewModel.onWorkoutDurationFocusChange(workoutDuration.text.toString())
        }
    }


    private fun addActivity() {
        addActivityButton.setOnClickListener {
            exerciseViewModel.addActivity(workoutDuration.toString().toInt())
            findNavController().popBackStack()
        }
    }
}

