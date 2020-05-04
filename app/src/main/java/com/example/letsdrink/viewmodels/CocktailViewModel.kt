package com.example.letsdrink.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.letsdrink.data.Drink
import com.example.letsdrink.data.Drinks
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.io.IOException
import java.time.LocalDate

class CocktailViewModel : ViewModel() {
    private val repository = CocktailRepository()
    private var isAlcoholicDrink = false;
    var isRandomDrinkModeActive = false;

    private val selectedDrink = MutableLiveData<List<Drink>>()
    private val alcoholicDrinks = MutableLiveData<List<Drink>>()
    private val nonAlcoholicDrinks = MutableLiveData<List<Drink>>()
    private val randomDrink = MutableLiveData<List<Drink>>()

    init {
        loadNonAlcoholicDrinks()
        loadAlcoholicDrinks()
        loadRandomDrink()
    }

    fun loadDrinkById(id: String) {
        CoroutineScope(viewModelScope.coroutineContext).launch {
            try {
                selectedDrink.postValue(repository.getDrinkById(id).drinks)
            }catch (e: IOException){
                Log.d("Tag", e.message.toString())
            }

        }
    }
    fun loadAlcoholicDrinks(){
        CoroutineScope(viewModelScope.coroutineContext).launch {
            try {
                alcoholicDrinks.postValue(repository.getAlcoholicDrinks().drinks)
            }catch (e: IOException){
                Log.d("Tag", e.message.toString())
            }
        }
    }
    fun loadNonAlcoholicDrinks(){
        CoroutineScope(viewModelScope.coroutineContext).launch {
            try {
                nonAlcoholicDrinks.postValue(repository.getNonAlcoholicDrinks().drinks)
            }catch (e: IOException){
                Log.d("Tag", e.message.toString())
            }
        }
    }
    fun loadRandomDrink(){
        CoroutineScope(viewModelScope.coroutineContext).launch {
            try {
                randomDrink.postValue(repository.getRandomDrink().drinks)
            }catch (e: IOException){
                Log.d("Tag", e.message.toString())
            }
        }
    }

    fun getAlcoholicDrinks(): LiveData<List<Drink>> = alcoholicDrinks
    fun getNonAlcoholicDrinks(): LiveData<List<Drink>> = nonAlcoholicDrinks
    fun getRandomDrink(): LiveData<List<Drink>> = randomDrink

    fun getSelectedDrink(): LiveData<List<Drink>> { return selectedDrink }

    fun setAlcoholicDrinkFilter(value: Boolean){ isAlcoholicDrink = value }
    fun getAlcoholicDrinksFilter() = isAlcoholicDrink


}