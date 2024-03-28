package com.example.myunsplashphotosapp.Paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.myunsplashphotosapp.Api.api_service
import com.example.myunsplashphotosapp.Utils
import com.example.myunsplashphotosapp.models_Random.User_Random

class wallpaperRandomPaging(var apiService: api_service):PagingSource<Int,User_Random>() {
    override fun getRefreshKey(state: PagingState<Int, User_Random>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User_Random> {
       return try {
           var position = params.key?:1
           var result = apiService.getpics(Utils.Api_Key)
           return LoadResult.Page(
               data = listOf(result),
               prevKey = if (position==1)null else position-1,
               nextKey = if (position==result.user?.total_photos)null else position+1
           )
       }catch (e:Exception){
           LoadResult.Error(e)
       }
    }
}