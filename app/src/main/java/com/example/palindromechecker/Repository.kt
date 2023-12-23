package com.example.palindromechecker

import android.provider.SyncStateContract.Constants
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.palindromechecker.data.api.ApiService
import com.example.palindromechecker.data.paging.UserPagingSource
import com.example.palindromechecker.data.response.DataItem

class Repository (private val apiService: ApiService){
    fun getAllUser():LiveData<PagingData<DataItem>>{
        return Pager(
            config = PagingConfig(
                initialLoadSize = 5,
                pageSize = 5
            ),

            pagingSourceFactory = {
                UserPagingSource(apiService)
            }
        ).liveData

    }
}