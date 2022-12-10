package com.example.muradsgraduationproject.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.muradsgraduationproject.data.FoodsResponse
import com.example.muradsgraduationproject.data.entity.Foods
import com.example.muradsgraduationproject.data.repo.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(var frepo: FoodRepository) : ViewModel() {

    private val _foodsList = MutableLiveData<FoodsResponse>()
    val foodsList: LiveData<FoodsResponse>
        get() = _foodsList

    init {
        getAllFoods()
    }

    private fun getAllFoods() {
        viewModelScope.launch {
            _foodsList.postValue(frepo.getAllFoods())
        }

    }
}