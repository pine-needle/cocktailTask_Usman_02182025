package com.example.cocktailtaskapp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CocktailDao {
    @Query("SELECT * FROM cocktails")
    suspend fun getCocktails(): List<CocktailTable>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCocktail(cocktail:CocktailTable)

    @Delete
    suspend fun deleteCocktail(cocktail: CocktailTable)
}