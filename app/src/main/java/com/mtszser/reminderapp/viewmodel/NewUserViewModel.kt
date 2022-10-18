package com.mtszser.reminderapp.viewmodel

import androidx.lifecycle.*
import com.mtszser.reminderapp.model.UserProfile
import com.mtszser.reminderapp.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


@HiltViewModel
class NewUserViewModel @Inject constructor(private val repo: UserRepository): ViewModel() {

    private val _state = MutableLiveData<StateOfUser>()
    val state = _state as LiveData<StateOfUser>

    init {

        viewModelScope.launch(Dispatchers.IO) {
            _state.postValue(StateOfUser.Loaded(
                userList = repo.getAll(),
            ))
        }
    }


    fun insert(userProfile: UserProfile) = viewModelScope.launch(Dispatchers.IO) {
        repo.insert(userProfile)
        _state.postValue(StateOfUser.Loaded(
            userList = listOf(userProfile)
        ))

    }

    fun countWater(weight: String): Int {
        val weights = weight.toInt()
        return weights * 35
    }


    fun getDate(): String {
        return repo.getDate()
    }


    sealed class StateOfUser{
        data class Loaded(
            val userList: List<UserProfile> = listOf(),
        ) :StateOfUser()
        object Error: StateOfUser()
        object Loading: StateOfUser()
    }


}