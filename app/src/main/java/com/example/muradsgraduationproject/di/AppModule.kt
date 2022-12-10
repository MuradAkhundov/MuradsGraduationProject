package com.example.muradsgraduationproject.di

import com.example.muradsgraduationproject.api.ApiUtils
import com.example.muradsgraduationproject.api.FoodService
import com.example.muradsgraduationproject.data.datasource.FoodDataSource
import com.example.muradsgraduationproject.data.repo.FoodRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideFoodRepository(fds :FoodDataSource) :FoodRepository{
        return FoodRepository(fds)
    }

    @Provides
    @Singleton
    fun provideFoodDataSource(fservice :FoodService):FoodDataSource{
        return FoodDataSource(fservice)
    }

    @Provides
    @Singleton
    fun provideFoodService() : FoodService {
      return ApiUtils.getFoodService()
    }




}