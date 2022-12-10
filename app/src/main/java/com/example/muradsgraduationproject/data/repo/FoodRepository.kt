package com.example.muradsgraduationproject.data.repo

import com.example.muradsgraduationproject.data.FoodsCartResponse
import com.example.muradsgraduationproject.data.FoodsResponse
import com.example.muradsgraduationproject.data.datasource.FoodDataSource
import com.example.muradsgraduationproject.data.entity.Foods

class FoodRepository(var fds :FoodDataSource) {

    suspend fun getAllFoods(): FoodsResponse = fds.getAllFood()

    suspend fun getAllFoodsCart(userName: String)  = fds.getAllFoodsCart(userName)

    suspend fun insert(
        name: String,
        image: String,
        price: Int,
        category: String,
        orderAmount: Int,
        userName: String
    ) =
        fds.insert(name, image, price, category, orderAmount, userName)

    suspend fun delete(cartId:Int,userName: String) = fds.delete(cartId,userName)




//        {
//        fdcs.insert(name, image, price, category, orderAmount, userName)
//        Log.e("Food Add ViewModel" , "Food is added Repo")
//    }
        }
