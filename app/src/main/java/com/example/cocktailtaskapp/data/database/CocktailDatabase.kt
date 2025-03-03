package com.example.cocktailtaskapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CocktailTable::class],
    version = 1
)

abstract class CocktailDatabase : RoomDatabase() {
    abstract fun getCocktailDao (): CocktailDao
}