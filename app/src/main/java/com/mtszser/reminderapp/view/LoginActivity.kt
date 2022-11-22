package com.mtszser.reminderapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
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
                    userViewModel.selectActivityLevelBonusWater(adapter.getItem(position) as BaseActivities)
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }

            }
        }

        userViewModel.usersState.observe(this, Observer { userState ->
            if (userState.userList.isEmpty()) {
                binding.mainLayout.visibility = View.VISIBLE
                binding.saveUser.setOnClickListener {
                    val drankWater = DrankWaterBase(0, 0, 0, "")
                    val waterList = WaterReminder(0, waterContainer = userViewModel.countWater(weight = weight),
                        userState.selectedActivityBonusWater!!.baseActivityBonusCalories, 0, userViewModel.getDate())
                    val userProfile = UserProfile(0, firstName = name, weight = weight, 0, waterList, drankWater)
                    userViewModel.insert(userProfile)
                }
            } else {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        })

    }
}