package com.mtszser.reminderapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.mtszser.reminderapp.databinding.ActivityLoginBinding
import com.mtszser.reminderapp.model.*
import com.mtszser.reminderapp.viewmodel.NewUserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val userViewModel: NewUserViewModel by viewModels()
    private lateinit var binding: ActivityLoginBinding

    private val name: String
        get() = binding.name.text.toString()

    private val weight: String
        get() = binding.weight.text.toString()

    private val height: String
        get() = binding.height.text.toString()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getProfile()

    }

    private fun getProfile() {
        userViewModel.state.observe(this, Observer { state ->
            when (state) {
                is NewUserViewModel.StateOfUser.Loaded -> {
                    if (state.userList.isEmpty()) {
                        binding.mainLayout.visibility = View.VISIBLE
                        binding.saveUser.setOnClickListener {
                            // zmienna z policzoną ilością wody do spożycia
                            val waterIntake = userViewModel.countWater(weight = weight)
                            val currentDate = userViewModel.getDate()
                            val drankWater = DrankWaterBase(0, 0, 0, "")
                            val waterList = WaterReminder(0, waterContainer = waterIntake, 0, 0, currentDate)
                            val userProfile = UserProfile(0, firstName = name, weight = weight, height = height, 0, waterList, drankWater)
                            userViewModel.insert(userProfile)
                        }
                    } else {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
                NewUserViewModel.StateOfUser.Error -> {}
                NewUserViewModel.StateOfUser.Loading -> {}
            }
        })

    }
}