package com.mtszser.reminderapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mtszser.reminderapp.model.ItemData

class ItemsViewModel: ViewModel() {

    private val items = MutableLiveData<List<ItemData>>()
    private val itemsLoadError = MutableLiveData<Boolean>()
    private val itemsLoading = MutableLiveData<Boolean>()


    fun refreshItems() {
        fetchItems()
    }

    private fun fetchItems() {
        itemsLoading.value = true

        val itemList = listOf(ItemData("Picie wody", "Cały Tydzień", "8:00-22:00"),
            ItemData("Branie Supli", "Cały Tydzień", "8:00-22:00"),
            ItemData("Mycie Zębów", "Cały Tydzień", "8:00-22:00"),
        )
        itemsLoadError.value = false
        itemsLoading.value = false
        items.value = itemList

    }


}