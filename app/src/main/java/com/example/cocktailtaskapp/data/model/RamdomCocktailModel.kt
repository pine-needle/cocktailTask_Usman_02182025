package com.example.cocktailtaskapp.data.model


import com.google.gson.annotations.SerializedName

data class RandomCocktailModel(
    val drinks: List<DrinkModel?> = listOf()
)
