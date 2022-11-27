package com.mtszser.reminderapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.activity.viewModels
import com.mtszser.reminderapp.databinding.ActivityLoginBinding
import com.mtszser.reminderapp.model.*
import com.mtszser.reminderapp.view.adapters.ActivitySpinnerAdapter
import com.mtszser.reminderapp.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var binding: ActivityLoginBinding

    private val name: String
        get() = binding.name.text.toString()

    private val weight: String
        get() = binding.weight.text.toString()

    private val activitySpinnerContainer
    get() = binding.activitySpinnerContainer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getProfile()

    }

    private fun getProfile() {
        activitySpinnerContainer.apply {
            adapter = ActivitySpinnerAdapter(context)
            activitySpinnerContainer.adapter = adapter
            activitySpinnerContainer.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, p3: Long) {
                    loginViewModel.selectActivityLevelBonusWater(adapter.getItem(position) as BaseActivities)
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }

            }
        }


        loginViewModel.usersState.observe(this) { userState ->
            if (userState.userList.isEmpty()) {
                binding.saveUser.setOnClickListener {
                    val drankWater = DrankWaterBase(0, 0, 0, "")
                    val waterList = WaterReminder(
                        0, waterContainer = loginViewModel.countWater(weight = weight),
                        userState.selectedActivityBonusWater?.baseActivityBonusCalories!!, 0, loginViewModel.getDate()
                    )
                    val userProfile =
                        UserProfile(0, firstName = name, weight = weight, 0, waterList, drankWater)
                    loginViewModel.insert(userProfile)
                }
            } else if (userState.userList.isNotEmpty()) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

    }

}