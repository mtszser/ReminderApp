package com.mtszser.reminderapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.mtszser.reminderapp.R
import com.mtszser.reminderapp.databinding.ActivityMainBinding
import com.mtszser.reminderapp.viewmodel.NewUserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val newUserViewModel: NewUserViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getNav()

    }

    private fun getNav() {
        newUserViewModel.getAll().observe(this, Observer {
            if (!it.isNullOrEmpty()){
                getNavigation()
                Log.i("duaa", "$it")
            } else{
                val navController = findNavController(R.id.fragment)
                navController.navigate(R.id.newUserFragment)
            }
        })


    }
    private fun getNavigation() {
        val bottomMenu = binding.bottomNav
        bottomMenu.visibility = View.VISIBLE
        val navController = findNavController(R.id.fragment)
        val startDestination = navController.graph.startDestinationId
        val navOptions = NavOptions.Builder().setPopUpTo(startDestination, true).build()
        navController.navigate(startDestination, null, navOptions)
        bottomMenu.setupWithNavController(navController)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.waterFragment, R.id.actionFragment,
            R.id.settingsFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)

    }


}