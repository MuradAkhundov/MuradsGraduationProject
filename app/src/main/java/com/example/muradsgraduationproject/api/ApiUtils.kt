package com.example.muradsgraduationproject.api

class ApiUtils {
    companion object {
        //http://kasimadalan.pe.hu/foods/getAllFoods.php
        val BASE_URL = "http://kasimadalan.pe.hu/foods/"

        fun getFoodService(): FoodService {
            return RetrofitInstance.getClient(BASE_URL).create(FoodService::class.java)
        }
//        fun getFoodCartService(): FoodCartService {
//            return RetrofitInstance.getClient(BASE_URL).create(FoodCartService::class.java)
//        }
    }

}