package com.example.palindromechecker.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.palindromechecker.data.di.Injection
import com.example.palindromechecker.ui.user.UserViewModel

class ViewModelFactory(private val context: Context):ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)){
            return UserViewModel(Injection.provideRepository(context)) as T
        }

        throw IllegalArgumentException("Uknown ViewModel Class : ${modelClass.name}")
    }
}