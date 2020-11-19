package com.example.jetpacknavigation.fakeuserexersize.services

import com.example.jetpacknavigation.fakeuserexersize.model.DataItem
import com.example.jetpacknavigation.fakeuserexersize.model.User
import retrofit2.Call
import retrofit2.http.*

interface UserServices {
    @GET("users")
    fun getAllUser(): Call<DataItem>

    @GET("users/{id}")
    fun getUserById(@Path("id") id: Int): Call<User>

    @POST("users")
    @Headers("Content-Type: application/json")
    fun insertUser(@Body productModel: User): Call<User>

    @PUT("users/{id}")
    @Headers("Content-Type: application/json")
    fun updateUserById(@Path("id") id: Int, @Body productModel: User): Call<User>

    @DELETE("users/{id}")
    fun deleteProductById(@Path("id") id: Int): Call<User>

}