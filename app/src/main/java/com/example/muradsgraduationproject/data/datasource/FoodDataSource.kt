package com.example.muradsgraduationproject.data.datasource

import android.util.Log
import com.example.muradsgraduationproject.api.FoodService
import com.example.muradsgraduationproject.data.FoodsCartResponse
import com.example.muradsgraduationproject.data.FoodsResponse
import com.example.muradsgraduationproject.data.entity.FoodsCart
import com.example.muradsgraduationproject.data.repo.CRUDResponse

class FoodDataSource(var api :FoodService) {
    //suspend fun getAllFood() :FoodsResponse = api.getAllFoods()

    suspend fun getAllFood() :FoodsResponse = api.getAllFoods()

    suspend fun getAllFoodsCart(userName: String) : List<FoodsCart> = api.getAllFoodsCart(userName).foods_cart

    suspend fun insert(name:String,image :String , price : Int, category : String,orderAmount : Int,userName : String) =
        api.insert(name, image, price, category, orderAmount, userName)

    suspend fun delete(cartId:Int,userName: String)  = api.delete(cartId,userName)
//    {
//        val response = api.insert(name, image, price, category, orderAmount, userName)
//        Log.e("Response","${response.success} - ${response.message}")
//
//    }


}