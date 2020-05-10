package com.example.letsdrink.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.letsdrink.data.Drink
import com.example.letsdrink.data.Drinks
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.lang.Exception
import java.time.LocalDate

class CocktailViewModel(application: Application) : AndroidViewModel(application) {
    private val repositoryDebug = "REPOSITORY_DEBUG"
    private val repository = CocktailRepository(application.applicationContext)
    private var isAlcoholicDrink = false;
    var isRandomDrinkModeActive = false;

    private val selectedDrink = MutableLiveData<List<Drink>>()
    private val alcoholicDrinks = MutableLiveData<List<Drink>>()
    private val nonAlcoholicDrinks = MutableLiveData<List<Drink>>()
    private val randomDrink = MutableLiveData<List<Drink>>()
    private val favouriteDrinks = MutableLiveData<List<Drink>>()

    init {
        loadNonAlcoholicDrinks()
        loadAlcoholicDrinks()
        loadRandomDrink()
        loadFavouriteDrinks()
    }

    fun setFavouriteDrinks(value: List<Drink>){
        favouriteDrinks.postValue(value)
    }

    fun setSelectedDrink(value: List<Drink>){
        selectedDrink.postValue(value)
    }

    //Fetch data
    fun loadDrinkById(id: String) {
        CoroutineScope(viewModelScope.coroutineContext).launch {
            try {
                selectedDrink.postValue(repository.getDrinkById(id).drinks)
            }catch (e: IOException){
                Log.d(repositoryDebug, e.message.toString())
            }

        }
    }
    fun loadAlcoholicDrinks(){
        CoroutineScope(viewModelScope.coroutineContext).launch {
            try {
                alcoholicDrinks.postValue(repository.getAlcoholicDrinks().drinks)
            }catch (e: IOException){
                Log.d(repositoryDebug, e.message.toString())
            }
        }
    }
    fun loadNonAlcoholicDrinks(){
        CoroutineScope(viewModelScope.coroutineContext).launch {
            try {
                nonAlcoholicDrinks.postValue(repository.getNonAlcoholicDrinks().drinks)
            }catch (e: IOException){
                Log.d(repositoryDebug, e.message.toString())
            }
        }
    }
    fun loadRandomDrink(){
        CoroutineScope(viewModelScope.coroutineContext).launch {
            try {
                randomDrink.postValue(repository.getRandomDrink().drinks)
            }catch (e: IOException){
                Log.d(repositoryDebug, e.message.toString())
            }
        }
    }
    fun loadFavouriteDrinks(){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                favouriteDrinks.postValue(repository.getAllFavDrinks())
            }catch (e: Exception){
                Log.d(repositoryDebug, e.message)
            }
        }
    }

    //Get data
    fun getAlcoholicDrinks(): LiveData<List<Drink>> = alcoholicDrinks
    fun getNonAlcoholicDrinks(): LiveData<List<Drink>> = nonAlcoholicDrinks
    fun getRandomDrink(): LiveData<List<Drink>> = randomDrink
    fun getSelectedDrink(): LiveData<List<Drink>> = selectedDrink

    //Set filter which data to expose
    fun setAlcoholicDrinkFilter(value: Boolean){ isAlcoholicDrink = value }
    fun getAlcoholicDrinksFilter() = isAlcoholicDrink

    //Database function
    fun insertFavDrink(drink: Drink){
        CoroutineScope(viewModelScope.coroutineContext).launch{
            repository.insertFavDrink(drink)
        }
    }
    fun deleteFavDrink(drink: Drink){
        CoroutineScope(viewModelScope.coroutineContext).launch {
            repository.deleteFavDrink(drink)
        }
    }
    fun getAllFavDrinks(): LiveData<List<Drink>> = favouriteDrinks



}