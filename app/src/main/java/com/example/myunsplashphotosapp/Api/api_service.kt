package com.example.myunsplashphotosapp.Api

import com.example.myunsplashphotosapp.Model_search.User_search
import com.example.myunsplashphotosapp.Modelss.Users
import com.example.myunsplashphotosapp.models.User5
import com.example.myunsplashphotosapp.models_Random.User_Random
import com.example.myunsplashphotosapp.models_category_recycle.user_category_recycle
import com.example.myunsplashphotosapp.models_catogory.user_category

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface api_service {
    @GET("/photos/")
    suspend fun getDataofImages(
        @Query("page")
        page:Int,
        @Query("per_page")
        per_page:Int=20,
        @Query("client_id")
        client_id:String
    ): Users

    @GET("/search/photos/")
    suspend fun getDataofImagesForCategory(
        @Query("query")
        query:String,
        @Query("page")
        page:Int=1,
        @Query("client_id")
        client_id:String
    ): user_category_recycle

    @GET("/search/photos/")
    suspend fun getSearchphotos2(
        @Query("query")
        query:String,
        @Query("page")
        page:Int=1,
        @Query("client_id")
        client_id:String
    ): User_search

    @GET("/collections/")
    suspend fun getCollection(
        @Query("page")
        page:Int,
        @Query("per_page")
        per_page:Int=20,
        @Query("client_id")
        client_id:String
    ):User5

    @GET("/photos/random/")
    suspend fun getpics(
        @Query("client_id")
        client_id:String
    ):User_Random

    @GET("/topics/")
    suspend fun get_Topics(
        @Query("page")
        page:Int,
        @Query("per_page")
        per_page:Int=20,
        @Query("client_id")
        client_id:String
    ):user_category
}