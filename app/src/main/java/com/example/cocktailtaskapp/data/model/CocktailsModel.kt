package com.example.cocktailtaskapp.data.model

import com.google.gson.annotations.SerializedName

data class CocktailsModel(
    @SerializedName("drinks")
    val drinks: List<DrinkModel?> = listOf()
)