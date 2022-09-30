package com.mtszser.reminderapp.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.mtszser.reminderapp.databinding.FragmentWaterBinding
import com.mtszser.reminderapp.model.UserProfile
import com.mtszser.reminderapp.model.WaterData
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class WaterViewModel(): ViewModel() {

    private var users = MutableLiveData<List<UserProfile>>()
    var _users = users as LiveData<List<UserProfile>>
    private val _items = MutableLiveData<List<WaterData>>()
    val items = _items as LiveData<List<WaterData>>
    private val _itemsLoadError = MutableLiveData<Boolean>()
    val itemsLoadError = _itemsLoadError as LiveData<Boolean>
    private val _itemsLoading = MutableLiveData<Boolean>()
    val itemsLoading = _itemsLoading as LiveData<Boolean>

//    private fun MutableLiveData<List<WaterData>>.asLiveData() = this as LiveData<List<WaterData>>


    fun refreshWater() {
        fetchWater()
    }



    private fun fetchWater() {
        _itemsLoading.value = true


        val itemList = listOf(WaterData("2500", "0"),
        )

        _itemsLoadError.value = false
        _itemsLoading.value = false
        _items.value = itemList


    }




}