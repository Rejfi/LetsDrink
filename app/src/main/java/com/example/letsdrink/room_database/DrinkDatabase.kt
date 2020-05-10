package com.example.letsdrink.room_database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.letsdrink.data.Drink
import kotlinx.coroutines.internal.synchronized


@Database(entities = [Drink::class], version = 1, exportSchema = true)
abstract class DrinkDatabase: RoomDatabase() {

    abstract fun drinkDao(): DrinkDao

    companion object{
        private var instance: DrinkDatabase? = null

        fun getInstance(context: Context): DrinkDatabase?{
            if(instance == null){
                instance = Room.databaseBuilder(context,
                    DrinkDatabase::class.java,
                    "FavouriteDrinksTable")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance
        }
    }

    fun deleteInstance(){
        instance = null
    }


}