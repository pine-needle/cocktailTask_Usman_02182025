package com.example.cocktailtaskapp.dashboard.api


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiReferenceConfig {
  //  https://www.thecocktaildb.com/api/json/v1/1/random.php
    private const val BASE_URL = "https://www.thecocktaildb.com/api/"
    const val END_POINT = "json/v1/1/random.php"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiConfigDetails: ApiConfigDetails = retrofit.create(ApiConfigDetails::class.java)
}