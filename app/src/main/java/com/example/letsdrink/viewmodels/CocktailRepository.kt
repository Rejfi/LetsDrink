package com.example.letsdrink.viewmodels

import com.example.letsdrink.data.Drinks
import com.example.letsdrink.network.RetrofitClient

class CocktailRepository {
    private val api = RetrofitClient.instance

    suspend fun getRandomDrink(): Drinks {
        return api.getRandomDrink().await()
    }

    suspend fun getAlcoholicDrinks(): Drinks{
        return api.getAlcoholicDrinks().await()
    }

    suspend fun getNonAlcoholicDrinks(): Drinks{
        return api.getNonAlcoholicDrinks().await()
    }

    suspend fun getDrinkById(id: String): Drinks{
        return api.getDrinkById(id).await()
    }
}