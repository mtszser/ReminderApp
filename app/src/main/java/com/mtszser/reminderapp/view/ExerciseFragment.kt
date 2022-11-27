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
import androidx.navigation.fragment.findNavController
import com.mtszser.reminderapp.R
import com.mtszser.reminderapp.databinding.FragmentExerciseBinding
import com.mtszser.reminderapp.model.DrankWaterBase
import com.mtszser.reminderapp.model.ExerciseBase
import com.mtszser.reminderapp.util.Const
import com.mtszser.reminderapp.view.adapters.ExercisesSpinnerAdapter
import com.mtszser.reminderapp.viewmodel.ExerciseViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.zip.Inflater

@AndroidEntryPoint
class ExerciseFragment : Fragment() {

    private lateinit var binding: FragmentExerciseBinding
    private val exerciseViewModel: ExerciseViewModel by viewModels()

    private val workoutDuration: String
        get() = binding.workoutDuration.text.toString()

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

    override fun onResume() {
        super.onResume()
        exerciseACTV.apply {
            setAdapter(ExercisesSpinnerAdapter(requireContext()))
            onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, p3: Long) {

                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        backButton.setOnClickListener {
            findNavController().popBackStack()
        }


    }
}

