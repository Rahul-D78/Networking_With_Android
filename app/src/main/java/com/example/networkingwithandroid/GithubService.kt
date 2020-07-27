package com.example.networkingwithandroid

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {

    @GET("users")
    suspend fun getUsers(): Response<List<User>>

    @GET("users/{id}")
    suspend fun getUsersById(@Path("id")id:String="abc"): Response<User>

    suspend fun searchUser(@Query("q")query: String):Response<List<User>>

}