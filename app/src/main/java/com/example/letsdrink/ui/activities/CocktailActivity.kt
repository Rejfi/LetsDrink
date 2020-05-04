package com.example.letsdrink.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.letsdrink.R
import com.example.letsdrink.network.RetrofitClient
import com.example.letsdrink.ui.fragments.CocktailListFragment
import com.example.letsdrink.ui.fragments.CriteriaFragment
import com.example.letsdrink.viewmodels.CocktailViewModel
import kotlinx.coroutines.runBlocking

class CocktailActivity : AppCompatActivity() {

    private lateinit var cocktailViewModel: CocktailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cocktailViewModel = ViewModelProvider(this)[CocktailViewModel::class.java]

        val criteriaFragment = CriteriaFragment()
        val cocktailListFragment = CocktailListFragment()

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction().apply {
                add(R.id.fragment_container, criteriaFragment, "CriteriaFragment")
                //addToBackStack("CriteriaFragmentStack")
                commit()
            }
        }
    }
}
