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
class CartViewModel @Inject constructor(var frepo : FoodRepository) :ViewModel() {
    private val _foodsCartList = MutableLiveData<List<FoodsCart>?>()
    val foodsCartList: LiveData<List<FoodsCart>?>
        get() = _foodsCartList

    init {
        getAllFoodsCart("Murad")

    }


     fun getAllFoodsCart(userName : String = "Murad") {
         CoroutineScope(Dispatchers.Main).launch {
             try {
             _foodsCartList.value = frepo.getAllFoodsCart(userName)

         } catch (e:Exception){
             _foodsCartList.value = mutableListOf()

         }
         }
     }
          fun delete(id:Int,userName: String = "murad"){
              CoroutineScope(Dispatchers.Main).launch {
                  frepo.delete(id,userName)
                  getAllFoodsCart("Murad")
              }
          }
    fun insert(

        foodCart: FoodsCart

    ) {
        CoroutineScope(Dispatchers.Main).launch {

            Log.e("Food Add ViewModel" , "Food is added ViewModel")
            frepo.insert(
                foodCart.name,
                foodCart.image,
                foodCart.price,
                foodCart.category,
                foodCart.orderAmount,
                foodCart.userName
            )
//            } catch (e: Exception) {

//            }
        }
    }
    }