package com.example.cocktailtaskapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cocktails")
data class CocktailTable(
    @PrimaryKey val drinkId:  String,
    val drinkName: String,
    val drinkInstructions: String,
    val drinkCategory: String,
    val drinkImage: String
)
