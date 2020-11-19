package com.example.jetpacknavigation.fakeuserexersize.services

import com.example.jetpacknavigation.fakeuserexersize.model.DataItem
import retrofit2.Call
import retrofit2.http.*

interface UserServices {
    @GET("users")
    fun getAllUser(): Call<DataItem>

    /*@GET("users")
    fun getAllUser(@Query("page") page: Int): Call<DataItem>
*/
    @PUT("products/{id}")
    fun updateUserById(@Path("id") id: Int, @Body productModel: DataItem): Call<DataItem>

    @DELETE("products/{id}")
    fun deleteUserById(@Path("id") id: Int): Call<DataItem>


    /*  @GET("users/{id}")
      fun getUserById(@Path("id") id: Int): Call<DataItem>

      @POST("users")
      fun insertUser(@Body dataItem: DataItem ): Call<DataItem>

      @PUT("users/{id}")
      fun updateUser(@Body updateUserBody: UpdateUserBody, @Path("id") id: Int): Call<UpdateUserResponse>

      @DELETE("users/{id}")
      fun deleteUser(@Path("id") id: Int): Call<String>*/
}