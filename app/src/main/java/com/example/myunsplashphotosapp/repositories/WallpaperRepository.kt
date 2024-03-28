package com.example.myunsplashphotosapp.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.myunsplashphotosapp.Api.api_service
import com.example.myunsplashphotosapp.Paging.searchpagong
import com.example.myunsplashphotosapp.Paging.wallpaperImageCollectionPaging
import com.example.myunsplashphotosapp.Paging.wallpaperPaging
import com.example.myunsplashphotosapp.Paging.wallpaperRandomPaging
import com.example.myunsplashphotosapp.Paging.wallpaperTopicsPaging
import com.example.myunsplashphotosapp.Paging.wallpaper_Category_Recycle_Paging
//import com.example.myunsplashphotosapp.Paging.wallpaperRandomPaging
import javax.inject.Inject

class WallpaperRepository @Inject constructor(private val apiService: api_service) {


    fun getPhotos()=Pager(
        config = PagingConfig(20),
        pagingSourceFactory = {wallpaperPaging(apiService)}
    ).liveData

    fun getcategoryphotos(searchtext:String)=Pager(
        config = PagingConfig(20),
        pagingSourceFactory = {wallpaper_Category_Recycle_Paging(apiService,searchtext)}
    ).flow

    fun getsearchphotos2(searchtext:String)=Pager(
        config = PagingConfig(20),
        pagingSourceFactory = {searchpagong(apiService,searchtext)}
    ).flow



    fun getCollectionePhotos()=Pager(
        config = PagingConfig(20),
        pagingSourceFactory = {wallpaperImageCollectionPaging(apiService)}
    ).liveData
    fun getRandomPhotos()=Pager(
        config = PagingConfig(20),
        pagingSourceFactory = {wallpaperRandomPaging(apiService)}
    ).liveData

    fun getTopics()=Pager(
        config = PagingConfig(20),
        pagingSourceFactory = {wallpaperTopicsPaging(apiService)}
    ).liveData
}