package com.mtszser.reminderapp.view

import android.app.AlertDialog
import android.content.DialogInterface
import android.media.MediaPlayer
import android.net.Uri
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
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mtszser.reminderapp.databinding.FragmentWaterBinding
import com.mtszser.reminderapp.model.DrankWaterBase
import com.mtszser.reminderapp.model.ExerciseBase
import com.mtszser.reminderapp.model.WaterContainers
import com.mtszser.reminderapp.viewmodel.WaterViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WaterFragment : Fragment() {

    private lateinit var mySpinner: Spinner
    private lateinit var adapter: ArrayAdapter<DrankWaterBase>
    private lateinit var binding: FragmentWaterBinding
    private lateinit var mediaPlayer: MediaPlayer
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
        getMediaPlayer()
        startWater()
        getSpinnerData()
        addExercise()
        getResultFromDialog()

    }

    private fun getRVList(waterContainerList: List<DrankWaterBase>) {
        Log.d("Lista drankwaterBase", "$waterContainerList")
        val recyclerView = binding.waterRecyclerView
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = ContRVAdapter(waterContainerList)

        }
    }

    private fun getResultFromDialog() {
        childFragmentManager.setFragmentResultListener("activityResult", viewLifecycleOwner) {key, bundle ->
            val result = bundle.getInt("waterIntake")
            val selectedItem = bundle.getString("selectedItem").toString()
            val exerciseBase = ExerciseBase(0, selectedItem, result)
            if (result.equals(null)) {
                Log.d("bundle", "nic tutaj niema!")
            } else {
                waterModel.insertExercise(exerciseBase)
                waterModel.addToBonusWaterContainer(result)
            }
        }
    }


    private fun addExercise() {
        binding.addActivityButton.setOnClickListener {
            val dialog = NewActivityDialog()
            dialog.show(childFragmentManager, "New Activity")

        }

    }

    private fun getMediaPlayer() {
        val filename = "android.resource://" + requireContext().packageName + "/raw/bubbles"
        mediaPlayer = MediaPlayer()
        mediaPlayer.setDataSource(requireContext(), Uri.parse(filename))
        mediaPlayer.prepare()
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
                    0 -> addOrDeleteWater(20, 0)
                    1 -> addOrDeleteWater(200, 1)
                    2 -> addOrDeleteWater(250, 2)
                    3 -> addOrDeleteWater(330, 3)
                    4 -> addOrDeleteWater(500, 4)
                    5 -> addOrDeleteWater(750, 5)
                    6 -> addOrDeleteWater(1000, 6)
                }

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

    }


    private fun addOrDeleteWater(i: Int, myPosition: Int) {
        val container = mySpinner.getItemAtPosition(myPosition)
        Log.d("container", "$container")
        binding.addWaterButton.setOnClickListener{
            waterModel.addWater(i, container as DrankWaterBase)
            mediaPlayer.start()

        }
        binding.waterDeleteButton.setOnClickListener{
            waterModel.deleteWater(i)
        }
    }



    private fun startWater() {
        waterModel.updateWater()
        waterModel.compareDates()
        waterModel.stateOfWater.observe(viewLifecycleOwner) {
            when (it) {
                is WaterViewModel.StateOfWater.Loaded -> {
                    if (listOf(it.countWaterList).isNotEmpty()) {
                        if (it.countWaterList?.alreadyDrank!! < 0) {
                            waterModel.resetDrankWater()
                            binding.waterDeleteButton.visibility = View.GONE
                        } else {
                            val waterContainer = waterModel.countEntireWaterContainer(it.countWaterList.waterContainer, it.countWaterList.bonusWaterContainer)
                            binding.waterContainer.text = it.countWaterList.alreadyDrank.toString() +
                                    " / " + waterContainer.toString() + "ml"
                            setProgressBar(waterContainer, it.countWaterList.alreadyDrank)
                            if(it.listOfWaterContainers.isNotEmpty()) {
                                getRVList(it.listOfWaterContainers)
                            } else {
                                Log.d("Water Containers", "It's empty")
                            }
                        }
                        if(it.countWaterList.alreadyDrank > 0) {
                            binding.waterDeleteButton.visibility = View.VISIBLE
                        } else if (it.countWaterList.alreadyDrank == 0) {
                            binding.waterDeleteButton.visibility = View.GONE
                        }

                    }
                }
                is WaterViewModel.StateOfWater.Loaded2 -> {

                }
                is WaterViewModel.StateOfWater.Loading -> {Toast.makeText(context, "dupa dupa dupa", Toast.LENGTH_SHORT).show()}

                WaterViewModel.StateOfWater.Error -> {}
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
                val builder = AlertDialog.Builder(activity)
                builder.setTitle("Confirm Reset")
                builder.setMessage("Are you sure you want to reset your water consumption?")
                builder.setPositiveButton("Yes", DialogInterface.OnClickListener{ dialog, id ->
                    dialog.cancel()
                    waterModel.resetCap()
                })
                builder.setNegativeButton("No", DialogInterface.OnClickListener{dialog, id ->
                    dialog.cancel()
                })
                val alert = builder.create()
                alert.show()
        }
        Log.d("progress" , "${progressBar.progress}")
    }


}



