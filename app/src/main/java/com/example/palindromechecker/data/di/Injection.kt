package com.example.palindromechecker.data.di

import android.content.Context
import com.example.palindromechecker.Repository
import com.example.palindromechecker.data.api.ApiConfig

object Injection {
    fun provideRepository(context: Context):Repository{
        val serviceApi = ApiConfig.getApiService()
        return Repository(serviceApi)
    }
}