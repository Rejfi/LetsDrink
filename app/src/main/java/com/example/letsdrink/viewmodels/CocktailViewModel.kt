package com.example.letsdrink.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.letsdrink.data.Drink
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.io.IOException
import java.time.LocalDate

class CocktailViewModel : ViewModel() {
    private val repository = CocktailRepository()
    private var isAlcoholicDrink = false;
    private val selectedDrink = MutableLiveData<List<Drink>>()

    fun getRandomDrink() = liveData {
        try {
            emit(repository.getRandomDrink().drinks)
        }catch (e: IOException){
            Log.d("Tag", e.message.toString())
        }

    }

    fun getAlcoholicDrinks() = liveData {
        try {
            emit(repository.getAlcoholicDrinks().drinks)
        }catch (e: IOException){
            Log.d("Tag", e.message.toString())
        }

    }

    fun getNonAlcoholicDrinks() = liveData {
        try {
            emit(repository.getNonAlcoholicDrinks().drinks)
        }catch (e: IOException){
            Log.d("Tag", e.message.toString())
        }

    }

    fun setDrinkById(id: String) {
        CoroutineScope(viewModelScope.coroutineContext).launch {
            try {
                selectedDrink.postValue(repository.getDrinkById(id).drinks)
            }catch (e: IOException){
                Log.d("Tag", e.message.toString())
            }

        }
    }

    fun getSelectedDrink(): LiveData<List<Drink>> { return selectedDrink }

    fun setAlcoholicDrinkFilter(value: Boolean){ isAlcoholicDrink = value }
    fun getAlcoholicDrinksFilter() = isAlcoholicDrink


}