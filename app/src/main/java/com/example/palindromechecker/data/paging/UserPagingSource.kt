package com.example.palindromechecker.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.palindromechecker.data.api.ApiService
import com.example.palindromechecker.data.response.DataItem
import com.example.palindromechecker.data.response.PagingUsersResponse

class UserPagingSource(private val apiService: ApiService):PagingSource<Int,DataItem>(){

    private companion object{
        const val INITIAL_PAGE_INDEX = 1
    }

    override fun getRefreshKey(state: PagingState<Int, DataItem>): Int? {
       return state.anchorPosition?.let { anchor ->
           val anchorPage = state.closestPageToPosition(anchor)
           anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
       }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataItem> {
        return try {
            val position = params.key ?: INITIAL_PAGE_INDEX
            val responseData = apiService.getAllUser(position).data

            LoadResult.Page(
                data = responseData,
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                nextKey = if (responseData.isNullOrEmpty()) null else position + 1
            )

        }catch (e : Exception){
            return LoadResult.Error(e)
        }
    }


}