package com.example.letsdrink.network

import com.example.letsdrink.data.Drinks
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface CocktailApi {

    //https://www.thecocktaildb.com/api/json/v1/1/random.php --> random drink link
    //https://www.thecocktaildb.com/api/json/v1/1/filter.php?a=Alcoholic --> list of alcoholic drinks
    //https://www.thecocktaildb.com/api/json/v1/1/filter.php?a=Non_Alcoholic --> list of non alcoholic drinks

    @GET("/api/json/v1/1/random.php")
    fun getRandomDrink(): Deferred<Drinks>

    @GET("/api/json/v1/1/filter.php?a=Alcoholic")
    fun getAlcoholicDrinks(): Deferred<Drinks>

    @GET("/api/json/v1/1/filter.php?a=Non_Alcoholic")
    fun getNonAlcoholicDrinks(): Deferred<Drinks>

    @GET("/api/json/v1/1/lookup.php?")
    fun getDrinkById(@Query("i") id: String): Deferred<Drinks>

}