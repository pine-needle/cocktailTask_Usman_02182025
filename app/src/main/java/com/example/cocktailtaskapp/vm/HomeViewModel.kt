package com.example.cocktailtaskapp.vm

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktailtaskapp.data.api.ApiDetails
import com.example.cocktailtaskapp.data.database.CocktailRepository
import com.example.cocktailtaskapp.data.database.CocktailTable
import com.example.cocktailtaskapp.data.model.DrinkModel
import com.example.cocktailtaskapp.utils.UiStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val cocktailRepository: CocktailRepository,
    private val apiDetails: ApiDetails
    ) : ViewModel() {

    private val _cocktailList = MutableLiveData<List<DrinkModel?>>()
    val cocktailList: LiveData<List<DrinkModel?>> get() =  _cocktailList

    private val _cocktailsFromDb: MutableLiveData<UiStatus<List<CocktailTable>>> = MutableLiveData(UiStatus.LOADING)
    val cocktailsFromDb: LiveData<UiStatus<List<CocktailTable>>> get() = _cocktailsFromDb

    private val _insertCocktailToDb: MutableLiveData<UiStatus<Unit>> = MutableLiveData(UiStatus.LOADING)
    val insertCocktailToDb: LiveData<UiStatus<Unit>> get() = _insertCocktailToDb

    private val _deleteCocktailToDb: MutableLiveData<UiStatus<Unit>> = MutableLiveData(UiStatus.LOADING)
    val deleteCocktailToDb: LiveData<UiStatus<Unit>> get() = _deleteCocktailToDb


    fun getCocktailData() {
        viewModelScope.launch(dispatcher) {
            try {
                val allCocktails = apiDetails.getCocktailByFirstLetter().drinks

                if (allCocktails.isEmpty()) {
                    Log.d("API Error", "getCocktailByFirstLetter: No data available")
                } else {
                    _cocktailList.postValue(allCocktails)
                    // Convert API data to Room Entity format
                    val cocktailsToSave = allCocktails.map { drinkModel ->
                        CocktailTable(
                            drinkId = drinkModel?.idDrink ?: "",  // Ensure non-null id
                            drinkName = drinkModel?.strDrink ?: "",
                            drinkCategory = drinkModel?.strCategory ?: "",
                            drinkInstructions = drinkModel?.strInstructions ?: "",
                            drinkImage = drinkModel?.strDrinkThumb ?: ""
                        )
                    }
                   //Save to room
                    cocktailsToSave.forEach{
                        cocktailRepository.insertCocktail(it)
                    }

                }
            } catch (e: Exception) {
                Log.e("API Error", "Error fetching cocktails: ${e.message}", e)
            }
        }
    }

    //Get Cocktails from DB
     fun getCocktailsFromDB(){
         viewModelScope.launch(dispatcher) {
             cocktailRepository.getCocktails().collect{
                 _cocktailsFromDb.postValue(it)
             }
         }
    }
    //Insert cocktail
    fun insertCocktailToDB(cocktailTable: CocktailTable, context: Context){
        viewModelScope.launch(dispatcher) {
            cocktailRepository.insertCocktail(cocktailTable)
        }
    }
    //Delete cocktail
    fun deleteCocktailFromDB(cocktailTable: CocktailTable){
        viewModelScope.launch(dispatcher) {
            cocktailRepository.deleteCocktail(cocktailTable)
        }
    }


}
