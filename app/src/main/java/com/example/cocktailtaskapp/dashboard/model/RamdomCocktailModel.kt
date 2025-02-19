package com.example.cocktailtaskapp.dashboard.model


import com.google.gson.annotations.SerializedName

data class RamdomCocktailModel(
    val drinks: List<DrinkModel?> = listOf()
)