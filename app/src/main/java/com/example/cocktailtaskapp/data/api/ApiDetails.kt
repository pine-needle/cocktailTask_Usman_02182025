package com.example.cocktailtaskapp.data.api

import com.example.cocktailtaskapp.data.model.CocktailsModel
import com.example.cocktailtaskapp.data.model.RandomCocktailModel
import retrofit2.http.GET

interface ApiDetails {
    //Get all cocktails
    @GET(ApiReference.COCKTAILS_END_POINT)
    suspend fun getCocktailByFirstLetter(): CocktailsModel

    //Get random cocktails
    @GET(ApiReference.RANDOM_END_POINT)
    suspend fun getRandomCocktail(): RandomCocktailModel
}