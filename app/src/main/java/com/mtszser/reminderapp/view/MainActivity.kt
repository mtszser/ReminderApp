package com.mtszser.reminderapp.view

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mtszser.reminderapp.R
import com.mtszser.reminderapp.databinding.ActivityMainBinding
import com.mtszser.reminderapp.viewmodel.NewUserViewModel
import com.mtszser.reminderapp.viewmodel.WaterViewModel
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

        val bottomMenu = binding.bottomNav
        val navController = findNavController(R.id.fragment)
        bottomMenu.setupWithNavController(navController)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.waterFragment, R.id.actionFragment,
            R.id.newUserFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)


    }




}