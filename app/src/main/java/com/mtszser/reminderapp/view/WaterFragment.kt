package com.mtszser.reminderapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
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
//        startModel()
        startWater()


    }

    private fun startWater() {

        waterModel.countWaterIntake()
        waterModel.allUsers.observe(viewLifecycleOwner, Observer {
            if (it.isNullOrEmpty()) {
                Log.i("dupa", "JEST PUSTE")
            }
        })
//        waterModel.allUsers.observe(viewLifecycleOwner, Observer {
//            if (it.isNullOrEmpty()){
//                Log.d("dupa", "to jest puste kurwa")
//            }
//        })

//        waterModel.stateOfWater.observe(viewLifecycleOwner, Observer { stateOfWater ->
//            val text = "${stateOfWater.waterIntake}"
//            Log.i("Water Fragment container", text)
//            binding.waterContainer.text = text
//        })
    }


    private fun startModel() {
//        waterModel.refreshWater()
//        waterModel.itemsLoadError.observe(viewLifecycleOwner, Observer { isError ->
//            isError?.let {
//                binding.listError.visibility = if (it) View.VISIBLE else View.GONE
//                binding.waterContainer.visibility = if (it) View.GONE else View.VISIBLE
//            }
//        })

//        waterModel.items.observe(viewLifecycleOwner, Observer {
//            if(it.isNotEmpty()){
//                binding.waterContainer.apply {
//                    waterModel.items.value?.forEach{items -> text = "${items.alreadyDrank}" + " / " +
//                            "${items.contCap}"}
//                }
//            }
//
//        })
    }
}

