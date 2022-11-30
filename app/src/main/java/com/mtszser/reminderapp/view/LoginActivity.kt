package com.mtszser.reminderapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.mtszser.reminderapp.R
import com.mtszser.reminderapp.databinding.ActivityLoginBinding
import com.mtszser.reminderapp.model.*
import com.mtszser.reminderapp.view.adapters.ActivitySpinnerAdapter
import com.mtszser.reminderapp.viewmodel.LoginValidationEvent
import com.mtszser.reminderapp.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var binding: ActivityLoginBinding


    private val userNameInputLayout
    get() = binding.nameInputLayout

    private val userName
    get() = binding.nameInput

    private val userWeightInputLayout
    get() = binding.userWeightInputLayout

    private val userWeight
    get() = binding.weight

    private val userActivityLevelACTV
    get() = binding.userActivityLevelACTV

    private val userActivityLevelLayout
    get() = binding.userActivityLevelLayout



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getProfile()
        inputValidation()
        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.loginValidationFlow.collect { event ->
                    when(event){
                        LoginValidationEvent.NameIsInvalid -> {userNameInputLayout.error = getString(R.string.please_enter_valid_name)}
                        LoginValidationEvent.NameIsEmpty -> userNameInputLayout.error = getString(R.string.name_is_empty)
                        LoginValidationEvent.NameIsValidated -> userNameInputLayout.error = null
                        LoginValidationEvent.WeightIsEmpty -> {userWeightInputLayout.error = getString(R.string.weight_is_empty)}
                        LoginValidationEvent.WeightIsValidated -> userWeightInputLayout.error = null
                        LoginValidationEvent.WeightIsInvalid -> userWeightInputLayout.error = getString(R.string.weight_is_invalid)
                        LoginValidationEvent.ActivityLevelIsNotPicked -> userActivityLevelLayout.error = getString(R.string.choose_activity_level)
                        LoginValidationEvent.ActivityLevelIsValidated -> userActivityLevelLayout.error = null
                    }
                }
            }
        }

    }


    private fun inputValidation() {
        userName.setOnFocusChangeListener { _, _ ->
            loginViewModel.onNameInputFocusChange(userName.text.toString())
        }
        userWeight.setOnFocusChangeListener { _, _ ->
            loginViewModel.onWeightInputFocusChange(userWeight.text.toString())
        }
        userActivityLevelACTV.setOnFocusChangeListener { _, _ ->
            loginViewModel.onActivityLevelInputFocusChange(userActivityLevelACTV.text.isEmpty())
        }
    }


    private fun getProfile() {

        userActivityLevelACTV.apply {
            setAdapter(ActivitySpinnerAdapter(context))
            setText("")
            setOnItemClickListener { adapterView, _, position, _ ->
                loginViewModel.selectActivityLevelBonusWater(adapterView.getItemAtPosition(position) as BaseActivities)
            }
        }


        loginViewModel.usersState.observe(this) { userState ->
            if (userState.userList.isEmpty()) {
                binding.saveUser.setOnClickListener {
                    val drankWater = DrankWaterBase(0, 0, 0, "")
                    val waterList = WaterReminder(
                        0, waterContainer = loginViewModel.countWater(weight = userWeight.text.toString()),
                        userState.selectedActivityBonusWater?.baseActivityBonusCalories!!, 0, loginViewModel.getDate()
                    )
                    val userProfile =
                        UserProfile(0, firstName = userName.text.toString(), weight = userWeight.text.toString(), 0, waterList, drankWater)
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