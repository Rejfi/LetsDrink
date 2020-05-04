package com.example.letsdrink.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    //https://www.thecocktaildb.com/api/json/v1/1/random.php --> random drink link
    //https://www.thecocktaildb.com/api/json/v1/1/filter.php?a=Alcoholic --> list of alcoholic cocktail
    //https://www.thecocktaildb.com/api/json/v1/1/filter.php?a=Non_Alcoholic --> list of alcoholic cocktail

    private val okHttpClient = OkHttpClient.Builder()
        .build()

    val instance by lazy{
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.thecocktaildb.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .build()

        retrofit.create(CocktailApi::class.java)
    }

}