package com.example.cocktailtaskapp.dashboard.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktailtaskapp.dashboard.api.ApiReferenceConfig
import com.example.cocktailtaskapp.dashboard.model.DrinkModel
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {

    private val _randomCocktail = MutableLiveData<List<DrinkModel?>>()
    val randomCocktail: LiveData<List<DrinkModel?>> = _randomCocktail

    fun getRandomCocktailData() {
        viewModelScope.launch {
            try {
                val randomCocktail = ApiReferenceConfig.apiConfigDetails.getRandomCoctails().drinks

                if (randomCocktail.isEmpty()) {
                    Log.d("API Error", "getCocktailByFirstLetter: No data available")
                } else {
                    _randomCocktail.postValue(randomCocktail)
                }
            } catch (e: Exception) {
                Log.e("API Error", "Error fetching cocktails: ${e.message}", e)
            }
        }
    }
}