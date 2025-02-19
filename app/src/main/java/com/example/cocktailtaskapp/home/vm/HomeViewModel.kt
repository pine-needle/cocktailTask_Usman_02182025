package com.example.cocktailtaskapp.home.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktailtaskapp.home.api.ApiReference
import com.example.cocktailtaskapp.home.model.CocktailsModel
import com.example.cocktailtaskapp.home.model.DrinkModel
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _cocktailList = MutableLiveData<List<DrinkModel?>>()
    val cocktailList: LiveData<List<DrinkModel?>> = _cocktailList

    fun getCocktailData() {
        viewModelScope.launch {
            try {
                val result = ApiReference.apiReference.getCocktailByFirstLetter().drinks

                if (result.isEmpty()) {
                    Log.d("API Error", "getCocktailByFirstLetter: No data available")
                } else {
                    _cocktailList.postValue(result)
                }
            } catch (e: Exception) {
                Log.e("API Error", "Error fetching cocktails: ${e.message}", e)
            }
        }
    }
}
