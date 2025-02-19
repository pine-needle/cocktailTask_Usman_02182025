package com.example.cocktailtaskapp.dashboard.api

import com.example.cocktailtaskapp.dashboard.model.RamdomCocktailModel
import retrofit2.http.GET


interface ApiConfigDetails {
    @GET(ApiReferenceConfig.END_POINT)
    suspend fun getRandomCoctails(): RamdomCocktailModel
}