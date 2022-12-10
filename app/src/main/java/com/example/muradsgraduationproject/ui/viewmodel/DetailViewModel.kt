package com.example.muradsgraduationproject.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.muradsgraduationproject.data.entity.FoodsCart
import com.example.muradsgraduationproject.data.repo.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(var frepo: FoodRepository) : ViewModel() {
    private val _foodsCartList = MutableLiveData<List<FoodsCart>?>()
    val foodsCartList: LiveData<List<FoodsCart>?>
        get() = _foodsCartList
//
//    private val _itemIsAdded: MutableLiveData<Boolean> = MutableLiveData(false)
//    val itemIsAdded: LiveData<Boolean> get() = _itemIsAdded

    init {

    }

    fun insert(

        foodCart: FoodsCart

    ) {
        CoroutineScope(Dispatchers.Main).launch {

            Log.e("murad", "Food is added ViewModel")
          val response = frepo.insert(
                foodCart.name,
                foodCart.image,
                foodCart.price,
                foodCart.category,
                foodCart.orderAmount,
                foodCart.userName
            )

//            if (response.success == 1) {
//                _itemIsAdded.postValue(true)
//            }
////            } catch (e: Exception) {
//
////            }
        }
    }


    fun getAllFoodsCart(userName: String = "Murad") {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                _foodsCartList.postValue(frepo.getAllFoodsCart(userName))

            } catch (e: Exception) {
                e.printStackTrace()
                _foodsCartList.postValue(listOf())

            }
        }
    }

    fun delete(id: Int, userName: String = "murad") {
        CoroutineScope(Dispatchers.Main).launch {
            frepo.delete(id, userName)
        }
    }
//    fun insert(
//        name: String,
//        image: String,
//        price: Int,
//        category: String,
//        orderAmount: Int,
//        userName: String
//    ) {
//        CoroutineScope(Dispatchers.Main).launch {
//            frepo.insert(name, image, price, category, orderAmount, userName)
//        }
//    }

//    fun resetLiveData() {
//        _foodsCartList.postValue(null)
//    }
}