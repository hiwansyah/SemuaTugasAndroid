package com.example.jetpacknavigation.fakeuserexersize.network

import com.example.jetpacknavigation.fakeuserexersize.services.UserServices
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UserClient {
    class service {
        companion object {
            private const val BASE_URL = "https://reqres.in/api/"

            val service by lazy {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL).addConverterFactory(
                        GsonConverterFactory.create(
                            GsonBuilder().setLenient().create()
                        )
                    )
                    .build()

                retrofit.create(UserServices::class.java)
            }
        }
    }
}