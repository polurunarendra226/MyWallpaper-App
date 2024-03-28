package com.example.myunsplashphotosapp.Paging


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.myunsplashphotosapp.Api.api_service
import com.example.myunsplashphotosapp.Utils
import com.example.myunsplashphotosapp.models_category_recycle.Result
import okhttp3.internal.toImmutableList

class wallpaper_Category_Recycle_Paging(var api:api_service, var query:String):PagingSource<Int, Result>() {

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
       return try {
           var position = params.key?:1
           var result = api.getDataofImagesForCategory(query = query, page = position, client_id = Utils.Api_Key)
           return LoadResult.Page(
               data = result.results,
               prevKey = if (position==1)null else position-1,
               nextKey = if (position==result.total)null else position+1
           )

       }catch (e:Exception){
           LoadResult.Error(e)
       }
    }
}