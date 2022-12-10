package com.example.muradsgraduationproject.api

import com.bumptech.glide.load.model.GlideUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {
        fun getClient(baseUrl: String): Retrofit {
            return Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create()).build()
        }
    }
}