package com.example.palindromechecker.data.api

import com.example.palindromechecker.data.response.DataItem
import com.example.palindromechecker.data.response.PagingUsersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/users")
    suspend fun getAllUser(
        @Query("page") page : Int,

    ) : PagingUsersResponse

}