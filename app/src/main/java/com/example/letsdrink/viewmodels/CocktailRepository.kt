package com.example.letsdrink.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.letsdrink.data.Drink
import com.example.letsdrink.data.Drinks
import com.example.letsdrink.network.RetrofitClient
import com.example.letsdrink.room_database.DrinkDao
import com.example.letsdrink.room_database.DrinkDatabase
import java.io.IOException
import java.lang.Exception

class CocktailRepository(context: Context) {
    private val api = RetrofitClient.instance
    private val drinkDao = DrinkDatabase.getInstance(context)!!.drinkDao()

    suspend fun getAllFavDrinks(): List<Drink>{
        return drinkDao.getAllFavDrinks()
    }

    suspend fun insertFavDrink(drink: Drink){
        drinkDao.insertFavDrink(drink)
    }

    suspend fun deleteFavDrink(drink: Drink){
        drinkDao.deleteFavDrink(drink)
    }

    suspend fun getRandomDrink(): Drinks {
        return api.getRandomDrink().await().body() ?: throw IOException()
    }

    suspend fun getAlcoholicDrinks(): Drinks{
        return api.getAlcoholicDrinks().await().body() ?: throw IOException()
    }

    suspend fun getNonAlcoholicDrinks(): Drinks{
        return api.getNonAlcoholicDrinks().await().body() ?: throw IOException()
    }

    suspend fun getDrinkById(id: String): Drinks{
        return api.getDrinkById(id).await().body() ?: throw IOException()
    }

}
