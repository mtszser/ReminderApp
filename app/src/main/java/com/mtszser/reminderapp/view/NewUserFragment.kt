package com.mtszser.reminderapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mtszser.reminderapp.R
import com.mtszser.reminderapp.databinding.FragmentNewUserBinding
import com.mtszser.reminderapp.model.ActionReminder
import com.mtszser.reminderapp.model.UserProfile
import com.mtszser.reminderapp.model.WaterReminder
import com.mtszser.reminderapp.viewmodel.NewUserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewUserFragment : Fragment() {


    private lateinit var binding: FragmentNewUserBinding
    private val userModel: NewUserViewModel by viewModels()

    private val name: String
        get() = binding.name.text.toString()

    private val weight: String
        get() = binding.weight.text.toString()

    private val height: String
        get() = binding.height.text.toString()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewUserBinding.inflate(inflater, container, false)
        val callBack = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.newUserFragment)
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(callBack)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        userModel.state.observe(viewLifecycleOwner){
            when(it){
                is NewUserViewModel.StateOfUser.Loaded -> {
                    if (it.userList.isEmpty()) {
                        Log.i("dupa", "jest puste")
                        binding.saveUser.setOnClickListener {
                            // zmienna z policzoną ilością wody do spożycia
                            val waterIntake = userModel.countWater(weight = weight)
                            val waterList = WaterReminder(0, waterContainer = waterIntake, 0)
                            val actionReminder = ActionReminder(0, "dupa", "dupa")
                            val userProfile = UserProfile(0, firstName = name, weight = weight, height = height,0 , waterList, actionReminder, )
                            userModel.insert(userProfile)
                        }
                    } else {
                        Log.i("dupa2", "działa")
                        val navigation = view.findNavController()
                        val bottomNav = activity?.findViewById<BottomNavigationView>(R.id.bottomNav)
                        bottomNav?.visibility = View.VISIBLE
                        val startDestination = navigation.graph.startDestinationId
                        val navOptions = NavOptions.Builder().setPopUpTo(startDestination, true).build()
                        navigation.navigate(startDestination, null, navOptions)
                        bottomNav?.setupWithNavController(navigation)
                        val appBarConfiguration = AppBarConfiguration(navigation.graph)
                        setupActionBarWithNavController(activity as AppCompatActivity, navigation, appBarConfiguration)
                    }
                }
                NewUserViewModel.StateOfUser.Error -> {}
                NewUserViewModel.StateOfUser.Loading -> {}
            }
        }



        }

    }






