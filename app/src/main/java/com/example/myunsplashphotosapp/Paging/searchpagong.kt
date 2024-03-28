package com.example.myunsplashphotosapp.Paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.myunsplashphotosapp.Api.api_service
import com.example.myunsplashphotosapp.Model_search.Result
import com.example.myunsplashphotosapp.Utils

class searchpagong(var apiService: api_service, var query:String):PagingSource<Int,Result>() {
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let { anchorPosition->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1) ?:
            state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            var position = params.key?:1
            var response =apiService.getSearchphotos2(query,page = position, client_id = Utils.Api_Key)
            return LoadResult.Page(
                data = response.results,
                prevKey = if (position==1)null else position-1,
                nextKey = if (position==response.total)null else position+1
            )
        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }


}