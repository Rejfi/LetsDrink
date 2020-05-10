package com.example.letsdrink.room_database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.letsdrink.data.Drink


@Dao
interface DrinkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavDrink(drink: Drink)

    @Delete
    suspend fun deleteFavDrink(drink: Drink)

    @Query("SELECT * FROM FavouriteDrinksTable")
    fun getAllFavDrinks(): List<Drink>

}