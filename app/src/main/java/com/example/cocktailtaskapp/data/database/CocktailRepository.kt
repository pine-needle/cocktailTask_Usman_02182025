package com.example.cocktailtaskapp.data.database

import com.example.cocktailtaskapp.utils.UiStatus
import kotlinx.coroutines.flow.Flow

interface CocktailRepository {
  //get cocktails
  suspend fun getCocktails() : Flow<UiStatus<List<CocktailTable>>>

  //insert cocktail
 suspend fun insertCocktail(cocktailTable: CocktailTable) : Flow<UiStatus<Unit>>

  //delete cocktail
  suspend fun deleteCocktail(cocktailTable: CocktailTable) : Flow<UiStatus<Unit>>
}