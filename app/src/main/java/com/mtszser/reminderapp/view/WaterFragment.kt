package com.mtszser.reminderapp.view

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mtszser.reminderapp.databinding.FragmentWaterBinding
import com.mtszser.reminderapp.model.DrankWaterBase
import com.mtszser.reminderapp.viewmodel.WaterViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WaterFragment : Fragment() {

    private lateinit var binding: FragmentWaterBinding
    private lateinit var mediaPlayer: MediaPlayer
    private val waterViewModel: WaterViewModel by viewModels()
    private lateinit var  waterAdapter: ContRVAdapter


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


        waterAdapter = ContRVAdapter(onItemClicked = waterViewModel::deleteWaterAmount)
        drankWaterActivityRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        drankWaterActivityRecyclerView.adapter = waterAdapter

        waterViewModel.waterState.observe(viewLifecycleOwner) { state ->
            drankWaterTextView.text = state.drankWaterLabel
            waterProgress.min = 0
            waterProgress.max = state.waterPerDay
            waterProgress.progress = state.alreadyDrank
            waterAdapter.submitList(state.drankWaterList)
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


        getMediaPlayer()
        addExercise()

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



