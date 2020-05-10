package com.example.letsdrink.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.letsdrink.R
import com.example.letsdrink.data.Drink
import com.example.letsdrink.ui.fragments.CocktailListFragment
import com.example.letsdrink.ui.fragments.CriteriaFragment
import com.example.letsdrink.viewmodels.CocktailViewModel

class CocktailActivity : AppCompatActivity() {

    private lateinit var cocktailViewModel: CocktailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cocktailViewModel = ViewModelProvider(this)[CocktailViewModel::class.java]


    }
}
