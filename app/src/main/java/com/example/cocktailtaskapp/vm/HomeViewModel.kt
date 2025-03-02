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
class HomeViewModel @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val apiDetails: ApiDetails
    ) : ViewModel() {

    private val _cocktailList = MutableLiveData<List<DrinkModel?>>()
    val cocktailList: LiveData<List<DrinkModel?>> get() =  _cocktailList

    fun getCocktailData() {
        viewModelScope.launch(dispatcher) {
            try {
                val allCocktails = apiDetails.getCocktailByFirstLetter().drinks

                if (allCocktails.isEmpty()) {
                    Log.d("API Error", "getCocktailByFirstLetter: No data available")
                } else {
                    _cocktailList.postValue(allCocktails)
                }
            } catch (e: Exception) {
                Log.e("API Error", "Error fetching cocktails: ${e.message}", e)
            }
        }
    }
}
