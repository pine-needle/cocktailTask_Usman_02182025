package com.example.cocktailtaskapp.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktailtaskapp.data.api.ApiDetails
import com.example.cocktailtaskapp.data.model.DrinkModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val apiDetails: ApiDetails
) : ViewModel() {

    private val _randomCocktail = MutableLiveData<List<DrinkModel?>>()
    val randomCocktail: LiveData<List<DrinkModel?>>  get() = _randomCocktail

    fun getRandomCocktailData() {
        viewModelScope.launch(dispatcher) {
            try {
                val randomCocktail = apiDetails.getRandomCocktail().drinks

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