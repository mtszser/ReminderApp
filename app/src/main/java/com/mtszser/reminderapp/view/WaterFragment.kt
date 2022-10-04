package com.mtszser.reminderapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mtszser.reminderapp.R
import com.mtszser.reminderapp.databinding.FragmentWaterBinding
import com.mtszser.reminderapp.model.UserProfile
import com.mtszser.reminderapp.viewmodel.WaterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WaterFragment : Fragment() {

    private lateinit var binding: FragmentWaterBinding
    private val waterModel: WaterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        binding = FragmentWaterBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startWater()




    }


    private fun startWater() {

        waterModel.countWaterIntake()
        waterModel.stateOfWater.observe(viewLifecycleOwner, Observer {
            val water = it.waterContainer?.toInt()
            val intake = water?.times(24)
            binding.waterContainer.text = intake.toString()
        })

    }

}


