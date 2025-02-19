package com.example.cocktailtaskapp.home.api

import com.example.cocktailtaskapp.home.model.CocktailsModel
import retrofit2.http.GET

interface ApiDetails {
    @GET(ApiReference.END_POINT)
    suspend fun getCocktailByFirstLetter(): CocktailsModel
}