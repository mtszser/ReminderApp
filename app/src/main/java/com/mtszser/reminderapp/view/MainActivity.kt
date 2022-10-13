package com.mtszser.reminderapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        newUserViewModel.state.observe(this, Observer { state ->
            when (state) {
                is NewUserViewModel.StateOfUser.Loaded -> {
                    if (state.userList.isEmpty()) {
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        getNavigation()
                    }
                }
                NewUserViewModel.StateOfUser.Error -> {}
                NewUserViewModel.StateOfUser.Loading -> {}
            }
        })
    }
    private fun getNavigation() {
        val bottomMenu = binding.bottomNav
        val navController = findNavController(R.id.fragment)
        val startDestination = navController.graph.startDestinationId
        val navOptions = NavOptions.Builder().setPopUpTo(startDestination, true).build()
        navController.navigate(startDestination, null, navOptions)
        bottomMenu.setupWithNavController(navController)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

    }


}