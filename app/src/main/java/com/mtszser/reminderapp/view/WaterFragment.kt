package com.mtszser.reminderapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations.distinctUntilChanged
import androidx.navigation.NavOptions
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mtszser.reminderapp.R
import com.mtszser.reminderapp.databinding.FragmentWaterBinding
import com.mtszser.reminderapp.model.UserProfile
import com.mtszser.reminderapp.model.WaterReminder
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
        getInfo()




    }

    private fun getInfo() {
        val repoWater = waterModel.allUsers.value
        binding.waterContainer.text = repoWater?.waterContainer.toString()
    }


    private fun startWater() {

        waterModel.stateOfWater.observe(viewLifecycleOwner) {
            when (it) {
                is WaterViewModel.StateOfWater.Loaded -> {
                    if (listOf(it.countWaterList).isNotEmpty()) {
                           binding.waterContainer.text = it.countWaterList?.waterContainer.toString()
                       }
                }
                is WaterViewModel.StateOfWater.Loaded2 -> {

                }
                WaterViewModel.StateOfWater.Error -> {}
                WaterViewModel.StateOfWater.Loading -> {}
                WaterViewModel.StateOfWater.IsEmpty -> {}
            }
        }
    }
}



