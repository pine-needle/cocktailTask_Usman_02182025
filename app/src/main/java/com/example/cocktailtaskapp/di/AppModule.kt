package com.example.cocktailtaskapp.di

import android.content.Context
import androidx.room.Room
import com.example.cocktailtaskapp.data.api.ApiDetails
import com.example.cocktailtaskapp.data.database.CocktailDao
import com.example.cocktailtaskapp.data.database.CocktailDatabase
import com.example.cocktailtaskapp.data.database.CocktailRepository
import com.example.cocktailtaskapp.data.database.CocktailRepositoryImpl
import com.example.cocktailtaskapp.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    //Gson Converter
    fun providesGson() : GsonConverterFactory = GsonConverterFactory.create()

    //Retrofit
    @Provides
    @Singleton
    fun providesRetrofit(
        gson: GsonConverterFactory
    ): Retrofit {
      return  Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(gson)
            .build()
    }
    //ApiReference
    @Provides
    @Singleton
    fun providesApiReference(
        retrofit: Retrofit
    ) : ApiDetails = retrofit.create(ApiDetails::class.java)

 //Dispatcher
    @Provides
    @Singleton
    fun providesCoroutineDispatcher() : CoroutineDispatcher = Dispatchers.IO

 //Provides cocktail database
    @Provides
    @Singleton
    fun providesCocktailDatabase(
        @ApplicationContext context: Context
    ) : CocktailDatabase = Room.databaseBuilder(
         context,
         CocktailDatabase::class.java,
         "cocktail-db"
    ).build()

    //provides CocktailDao
    @Provides
    @Singleton
    fun providesCocktailDao(
        cocktailDatabase: CocktailDatabase
    ) : CocktailDao = cocktailDatabase.getCocktailDao()

    //provides cocktailRepository
    @Provides
    @Singleton
    fun providesCocktailRepository(
        dao: CocktailDao
    ) : CocktailRepository = CocktailRepositoryImpl(dao)


}