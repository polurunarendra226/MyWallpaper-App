package com.example.myunsplashphotosapp.Paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.myunsplashphotosapp.Api.api_service
import com.example.myunsplashphotosapp.Modelss.UsersItem
import com.example.myunsplashphotosapp.Utils

class wallpaperPaging(var api:api_service):PagingSource<Int, UsersItem>() {

    override fun getRefreshKey(state: PagingState<Int, UsersItem>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UsersItem> {
       return try {
           var position = params.key?:1
           var result = api.getDataofImages(page = position, client_id = Utils.Api_Key)
           return LoadResult.Page(
               data = result.subList(0,20),
               prevKey = if (position==1)null else position-1,
               nextKey = if (position==result.get(position).user.total_photos)null else position+1
           )

       }catch (e:Exception){
           LoadResult.Error(e)
       }
    }
}