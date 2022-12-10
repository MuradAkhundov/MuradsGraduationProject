package com.example.muradsgraduationproject.api

import com.example.muradsgraduationproject.data.FoodsCartResponse
import com.example.muradsgraduationproject.data.FoodsResponse
import com.example.muradsgraduationproject.data.entity.Foods
import com.example.muradsgraduationproject.data.repo.CRUDResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

//http://kasimadalan.pe.hu/foods/getAllFoods.php
interface FoodService {
    @GET("getAllFoods.php")
    suspend fun getAllFoods(): FoodsResponse

    @POST("getFoodsCart.php")
    @FormUrlEncoded
    suspend fun getAllFoodsCart(@Field("userName") userName : String = "Murad"): FoodsCartResponse



    @POST("insertFood.php")
    @FormUrlEncoded
    suspend fun insert(@Field("name") name:String,
                       @Field("image") image :String,
                       @Field("price") price:Int,
                       @Field("category")category:String,
                       @Field("orderAmount")orderAmount :Int,
                       @Field("userName") userName : String
    ) : CRUDResponse


    @POST("deleteFood.php")
    @FormUrlEncoded
    suspend fun delete(@Field("cartId") cartId:Int,
                       @Field("userName") userName :String) : CRUDResponse
}