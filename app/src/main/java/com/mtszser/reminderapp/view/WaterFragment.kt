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
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.mtszser.reminderapp.R
import com.mtszser.reminderapp.databinding.FragmentWaterBinding
import com.mtszser.reminderapp.model.UserProfile
import com.mtszser.reminderapp.model.WaterContainers
import com.mtszser.reminderapp.model.WaterReminder
import com.mtszser.reminderapp.viewmodel.WaterViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@AndroidEntryPoint
class WaterFragment : Fragment() {

    private lateinit var mySpinner: Spinner
    private lateinit var adapter: ArrayAdapter<WaterContainers>
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
        getButtons()
        getSpinnerData()




    }

    private fun getSpinnerData() {
        val spinner = waterModel.updateSpinner()
        mySpinner = binding.changeContainer
        adapter = ArrayAdapter(
            requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            spinner
        )
        waterModel.position.observe(viewLifecycleOwner, Observer { position ->
            mySpinner.getItemAtPosition(position)
        })
        mySpinner.adapter = adapter
        mySpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, p3: Long) {
                Log.d("zmiana", "$position")
                waterModel.saveSpinnerPos(position)
                waterModel.updateSpinnerPosition(position)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }


    }


    private fun getButtons() {
        binding.addWaterButton.setOnClickListener{
            addWater(100)
        }
    }

    private fun addWater(i: Int) {
        waterModel.addWater(i)
    }

    private fun getInfo() {
        val repoWater = waterModel.allUsers.value
        binding.waterContainer.text = repoWater?.alreadyDrank.toString() + " / " +
                repoWater?.waterContainer.toString() + "ml"
    }


    private fun startWater() {
        waterModel.updateWater()
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
        Log.d("progress" , "${progressBar.progress}")
    }

}



