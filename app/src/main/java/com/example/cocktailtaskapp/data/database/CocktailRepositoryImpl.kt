package com.example.cocktailtaskapp.data.database

import com.example.cocktailtaskapp.utils.UiStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CocktailRepositoryImpl @Inject constructor(
    private val dao : CocktailDao
) : CocktailRepository {
      override suspend fun getCocktails(): Flow<UiStatus<List<CocktailTable>>> = flow{
       emit(UiStatus.LOADING)
        try {
            val cocktails = dao.getCocktails()
            emit(UiStatus.SUCCESS(cocktails))

        } catch (e:Exception) {
         emit(UiStatus.ERROR(e.toString()))
        }
    }

    override suspend fun insertCocktail(cocktailTable: CocktailTable): Flow<UiStatus<Unit>> = flow {
        emit(UiStatus.LOADING)
        try {
            dao.insertCocktail(cocktailTable)
            emit(UiStatus.SUCCESS(Unit))
        } catch (e:Exception) {
            emit(UiStatus.ERROR(e.toString()))
        }
    }

    override suspend fun deleteCocktail(cocktailTable: CocktailTable): Flow<UiStatus<Unit>>  = flow{
        emit(UiStatus.LOADING)
        try {
           dao.deleteCocktail(cocktailTable)
            emit(UiStatus.SUCCESS(Unit))
        } catch (e: Exception){
            emit(UiStatus.ERROR(e.toString()))
        }
    }
}