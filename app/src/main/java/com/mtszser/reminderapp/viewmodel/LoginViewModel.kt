package com.mtszser.reminderapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mtszser.reminderapp.model.BaseActivities
import com.mtszser.reminderapp.model.UserProfile
import com.mtszser.reminderapp.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(private val repo: UserRepository): ViewModel() {

    private val _userState = MutableLiveData(StateOfUser())
    val usersState = _userState as LiveData<StateOfUser>

    private val _loginValidationFlow = MutableSharedFlow<LoginValidationEvent>()
    val loginValidationFlow = _loginValidationFlow as SharedFlow<LoginValidationEvent>

    private var nameValidated: Boolean = false
    private var weightValidated: Boolean = false
    private var activityLevelValidated: Boolean = false

    init {
        loadProfile()
    }

    private fun loadProfile() {
        viewModelScope.launch {
            _userState.value = _userState.value?.copy(
                userList = repo.getAll()
            )
        }
    }

    fun countWater(weight: String): Int {
        val weights = weight.toInt()
        return weights * 35
    }

    fun selectActivityLevelBonusWater(basicActivities: BaseActivities) {
        viewModelScope.launch {
            _userState.value = _userState.value?.copy(
                selectedActivityBonusWater = basicActivities
            )
        }
    }

    fun getDate(): String {
        return repo.getDate()
    }

    fun insert(userProfile: UserProfile) = viewModelScope.launch {
        repo.insert(userProfile)
        _userState.value = _userState.value?.copy(
            userList = listOf(userProfile)
        )

    }


    fun onNameInputFocusChange(userName: String) {
        viewModelScope.launch {
            if(userName.isEmpty()) {
                _loginValidationFlow.emit(LoginValidationEvent.NameIsEmpty)
            } else if(userName.length > 16 || !userName.all { it.isLetter() } || userName.length < 3){
                _loginValidationFlow.emit(LoginValidationEvent.NameIsInvalid)
            } else {
                nameValidated = true
                _loginValidationFlow.emit(LoginValidationEvent.NameIsValidated)
            }
        }
    }

    fun onWeightInputFocusChange(userWeight: String){
        viewModelScope.launch {
            if(userWeight.isEmpty()){
                _loginValidationFlow.emit(LoginValidationEvent.WeightIsEmpty)
            } else if(userWeight.toInt() !in 41..200) {
                _loginValidationFlow.emit(LoginValidationEvent.WeightIsInvalid)
            } else {
                weightValidated = true
                _loginValidationFlow.emit(LoginValidationEvent.WeightIsValidated)
            }
        }
    }

    fun onActivityLevelInputFocusChange(isEmpty: Boolean) {
        viewModelScope.launch {
            if(isEmpty){
                _loginValidationFlow.emit(LoginValidationEvent.ActivityLevelIsNotPicked)
            } else {
                activityLevelValidated = true
                _loginValidationFlow.emit(LoginValidationEvent.ActivityLevelIsValidated)
            }

        }
    }

    fun checkIfInputsAllValidated(): Boolean {
        if(nameValidated && weightValidated && activityLevelValidated) {
            return true
        }
        return false
    }
}

data class StateOfUser(
    val selectedActivityBonusWater: BaseActivities? = null,
    val userList: List<UserProfile> = listOf(),
)

sealed class LoginValidationEvent{
    object NameIsEmpty: LoginValidationEvent()
    object NameIsInvalid: LoginValidationEvent()
    object NameIsValidated: LoginValidationEvent()

    object WeightIsEmpty: LoginValidationEvent()
    object WeightIsInvalid: LoginValidationEvent()
    object WeightIsValidated: LoginValidationEvent()

    object ActivityLevelIsValidated: LoginValidationEvent()
    object ActivityLevelIsNotPicked: LoginValidationEvent()
}
