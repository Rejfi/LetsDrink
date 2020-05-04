package com.example.letsdrink.viewmodels

import com.example.letsdrink.data.Drinks
import com.example.letsdrink.network.RetrofitClient
import java.io.IOException
import java.lang.Exception

class CocktailRepository {
    private val api = RetrofitClient.instance

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
