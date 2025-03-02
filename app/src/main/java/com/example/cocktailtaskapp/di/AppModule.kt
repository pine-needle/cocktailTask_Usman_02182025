package com.example.cocktailtaskapp.di

import com.example.cocktailtaskapp.data.api.ApiDetails
import com.example.cocktailtaskapp.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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

    @Provides
    @Singleton
    fun providesCoroutineDispatcher() : CoroutineDispatcher = Dispatchers.IO



    @Provides
    @Singleton
    fun providesApiReference(
        retrofit: Retrofit
    ) : ApiDetails = retrofit.create(ApiDetails::class.java)


}