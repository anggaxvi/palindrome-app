package com.example.palindromechecker.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.palindromechecker.Repository
import com.example.palindromechecker.data.response.DataItem

class UserViewModel(private val repository: Repository):ViewModel() {


    val user:LiveData<PagingData<DataItem>> =
        repository.getAllUser().cachedIn(viewModelScope)
}