package com.mtszser.reminderapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mtszser.reminderapp.model.BaseActivities
import com.mtszser.reminderapp.model.UserProfile
import com.mtszser.reminderapp.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(private val repo: UserRepository): ViewModel() {

    private val _userState = MutableLiveData(StateOfUser())
    val usersState = _userState as LiveData<StateOfUser>

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

}

data class StateOfUser(
    val selectedActivityBonusWater: BaseActivities? = null,
    val userList: List<UserProfile> = listOf(),
)