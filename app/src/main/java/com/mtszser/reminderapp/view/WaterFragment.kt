package com.mtszser.reminderapp.view

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.mtszser.reminderapp.databinding.FragmentWaterBinding
import com.mtszser.reminderapp.model.DrankWaterBase
import com.mtszser.reminderapp.model.DrankWaterView
import com.mtszser.reminderapp.viewmodel.WaterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


@AndroidEntryPoint
class WaterFragment : Fragment() {

    private lateinit var mySpinner: Spinner
    private lateinit var binding: FragmentWaterBinding
    private lateinit var mediaPlayer: MediaPlayer
    private var time: String = ""
    private val waterViewModel: WaterViewModel by viewModels()
    private lateinit var  adapter: ContRVAdapter


    private val drankWaterActivityRecyclerView
    get() = binding.waterRecyclerView

    private val waterAmountSpinner
    get() = binding.changeContainer

    private val addWaterAmountButton
    get() = binding.addWaterButton

    private val addDrankWaterActivityButton
    get() = binding.addActivityButton

    private val clearWaterAmountButton
    get() = binding.waterClearButton

    private val deleteWaterAmountButton
    get() = binding.waterDeleteButton

    private val waterProgress
    get() = binding.waterProgressBar

    private val drankWaterTextView
    get() = binding.drankWaterTV


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

        adapter = ContRVAdapter(onItemClicked = {
            waterViewModel.passUnitToFlow()
            lifecycleScope.launchWhenStarted {
                waterViewModel.unitFlow.collect {
                    NewActivityDialog().show(this@WaterFragment.parentFragmentManager, null)
                }
            }



        })


        drankWaterActivityRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        drankWaterActivityRecyclerView.adapter = adapter

        waterViewModel.waterState.observe(viewLifecycleOwner) { state ->
            drankWaterTextView.text = state.drankWaterLabel
            adapter.submitList(state.drankWaterList)
            }






        waterAmountSpinner.apply {
            adapter = ContainerAdapter(requireContext())
            waterAmountSpinner.adapter = adapter
            waterAmountSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, p3: Long) {

                    waterViewModel.selectAmountOfWater(adapter.getItem(position) as DrankWaterBase)
                    Log.d("selectedItem", "${adapter.getItem(position) as DrankWaterBase}")

                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
        }

        drankWaterActivityRecyclerView.setOnClickListener{
        }

        addWaterAmountButton.setOnClickListener {
            waterViewModel.addWaterAmount()
        }

        addDrankWaterActivityButton.setOnClickListener {
        }

        clearWaterAmountButton.setOnClickListener {
            waterViewModel.clearWaterAmount()
        }

        deleteWaterAmountButton.setOnClickListener {
            waterViewModel.deleteWaterAmount()
        }


        getMediaPlayer()
        addExercise()
//        getResultFromDialog()

    }


//    private fun getResultFromDialog() {
//        childFragmentManager.setFragmentResultListener("activityResult", viewLifecycleOwner) {key, bundle ->
//            val result = bundle.getInt("waterIntake")
//            val selectedItem = bundle.getString("selectedItem").toString()
//            val exerciseBase = ExerciseBase(0, selectedItem, result)
//            if (result.equals(null)) {
//                Log.d("bundle", "nic tutaj niema!")
//            } else {
//                waterModel.insertExercise(exerciseBase)
//                waterModel.addToBonusWaterContainer(result)
//            }
//        }
//    }


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



//    private fun setProgressBar(waterContainer: Int?, alreadyDrank: Int?) {
//        Log.d("progress bar" , "$alreadyDrank")
//        val progressBar = binding.waterProgressBar
//        progressBar.min = 0
//        if (waterContainer != null && alreadyDrank != null) {
//            progressBar.max = waterContainer
//            progressBar.progress = alreadyDrank
//        }
//        binding.waterClearButton.setOnClickListener{
//                val builder = AlertDialog.Builder(activity)
//                builder.setTitle("Confirm Reset")
//                builder.setMessage("Are you sure you want to reset your water consumption?")
//                builder.setPositiveButton("Yes", DialogInterface.OnClickListener{ dialog, id ->
//                    dialog.cancel()
//                    waterModel.resetCap()
//                })
//                builder.setNegativeButton("No", DialogInterface.OnClickListener{dialog, id ->
//                    dialog.cancel()
//                })
//                val alert = builder.create()
//                alert.show()
//        }
//        Log.d("progress" , "${progressBar.progress}")
//    }


}



