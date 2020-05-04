package com.example.letsdrink.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.letsdrink.data.Drink
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class CocktailViewModel : ViewModel() {
    private val repository = CocktailRepository()
    private var isAlcoholicDrink = false;
    private val selectedDrink = MutableLiveData<List<Drink>>()

    fun getRandomDrink() = liveData {
        emit(repository.getRandomDrink().drinks)
    }

    fun getAlcoholicDrinks() = liveData {
        emit(repository.getAlcoholicDrinks().drinks)
    }

    fun getNonAlcoholicDrinks() = liveData {
        emit(repository.getNonAlcoholicDrinks().drinks)
    }

    fun setDrinkById(id: String) {
        CoroutineScope(viewModelScope.coroutineContext).launch {
            selectedDrink.postValue(repository.getDrinkById(id).drinks)
        }
    }

    fun getSelectedDrink(): LiveData<List<Drink>> { return selectedDrink }

    fun setAlcoholicDrinkFilter(value: Boolean){ isAlcoholicDrink = value }
    fun getAlcoholicDrinksFilter() = isAlcoholicDrink


}