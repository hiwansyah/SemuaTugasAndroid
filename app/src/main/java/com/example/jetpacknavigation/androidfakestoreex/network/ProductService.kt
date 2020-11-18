package com.example.jetpacknavigation.androidfakestoreex.network

import com.example.jetpacknavigation.androidfakestoreex.model.Product
import retrofit2.Call
import retrofit2.http.GET

interface ProductService {
    @GET("products")
    fun getAllproduct(): Call<List<Product>>
}
