package com.example.cocktailtaskapp.home.model


import com.google.gson.annotations.SerializedName

data class CocktailsModel(
    @SerializedName("drinks")
    val drinks: List<DrinkModel?> = listOf()
)