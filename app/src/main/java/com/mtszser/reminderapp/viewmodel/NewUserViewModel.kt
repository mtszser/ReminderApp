package com.mtszser.reminderapp.viewmodel

import androidx.lifecycle.*
import com.mtszser.reminderapp.model.BaseActivities
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

    private val _usersState = MutableLiveData(StateOfUsers())
    val usersState = _usersState as LiveData<StateOfUsers>


    init {
        loadProfile()
    }

    private fun loadProfile() {
        viewModelScope.launch{
            _usersState.value = _usersState.value?.copy(
                userList = repo.getAll()
            )
        }
    }


}

data class StateOfUsers(
    val userList: List<UserProfile> = listOf(),
)