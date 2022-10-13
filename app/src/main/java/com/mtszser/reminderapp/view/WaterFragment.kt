package com.mtszser.reminderapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.mtszser.reminderapp.databinding.FragmentWaterBinding
import com.mtszser.reminderapp.model.WaterContainers
import com.mtszser.reminderapp.viewmodel.WaterViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WaterFragment : Fragment() {

    private lateinit var mySpinner: Spinner
    private lateinit var adapter: ArrayAdapter<WaterContainers>
    private lateinit var binding: FragmentWaterBinding
    private val waterModel: WaterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        // Inflate the layout for this fragment
        binding = FragmentWaterBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startWater()
        getSpinnerData()

    }

    private fun getSpinnerData() {
        val spinner = waterModel.updateSpinner()
        mySpinner = binding.changeContainer
        adapter = ContainerAdapter(requireContext(), spinner)
        mySpinner.adapter = adapter
        waterModel.getPosition()
        waterModel.position.observe(viewLifecycleOwner, Observer { position ->
            mySpinner.setSelection(position)
        })
        mySpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, p3: Long) {
                waterModel.updateSpinnerPosition(position)
                waterModel.saveSpinnerPos(position)
                when(position) {
                    0 -> addWater(200)
                    1 -> addWater(250)
                    2 -> addWater(330)
                    3 -> addWater(500)
                    4 -> addWater(750)
                    5 -> addWater(1000)
                }

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

    }

    private fun addWater(i: Int) {
        binding.addWaterButton.setOnClickListener{
            waterModel.addWater(i)
        }
    }



    private fun startWater() {
        waterModel.updateWater()
        waterModel.compareDates()
        waterModel.stateOfWater.observe(viewLifecycleOwner) {
            when (it) {
                is WaterViewModel.StateOfWater.Loaded -> {
                    if (listOf(it.countWaterList).isNotEmpty()) {
                           binding.waterContainer.text = it.countWaterList?.alreadyDrank.toString() +
                                   " / " + it.countWaterList?.waterContainer.toString() + "ml"
                        setProgressBar(it.countWaterList?.waterContainer, it.countWaterList?.alreadyDrank)
                       }
                }
                is WaterViewModel.StateOfWater.Loaded2 -> {

                }

                WaterViewModel.StateOfWater.Error -> {}
                WaterViewModel.StateOfWater.Loading -> {binding.waterLayout.visibility = View.GONE}
                WaterViewModel.StateOfWater.IsEmpty -> {}
            }
        }
    }

    private fun setProgressBar(waterContainer: Int?, alreadyDrank: Int?) {
        Log.d("progress bar" , "$alreadyDrank")
        val progressBar = binding.waterProgressBar
        progressBar.min = 0
        if (waterContainer != null && alreadyDrank != null) {
            progressBar.max = waterContainer
            progressBar.progress = alreadyDrank
        }
        binding.waterClearButton.setOnClickListener{
            if (alreadyDrank == 0){
                Toast.makeText(context, "You can't reset 0 dummy :)", Toast.LENGTH_SHORT).show()
            } else {
                waterModel.resetCap()
            }
        }
        Log.d("progress" , "${progressBar.progress}")
    }

}



