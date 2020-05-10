package com.example.letsdrink.ui.fragments

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.letsdrink.R
import com.example.letsdrink.isOnline
import com.example.letsdrink.viewmodels.CocktailViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_criteria.*
import java.net.ConnectException

class CriteriaFragment : Fragment() {

    private lateinit var cocktailViewModel: CocktailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_criteria, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cocktailViewModel = ViewModelProvider(requireActivity())[CocktailViewModel::class.java]
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        alcoImage.setOnClickListener {
            if(requireActivity().application.isOnline()) {
                cocktailViewModel.setAlcoholicDrinkFilter(true)
                if (cocktailViewModel.getAlcoholicDrinks().value.isNullOrEmpty()) cocktailViewModel.loadAlcoholicDrinks()
                this.findNavController().navigate(R.id.cocktailListFragment)
            }
            else Snackbar.make(requireView(), "There is no Internet my friend, not drinking today",Snackbar.LENGTH_LONG).show()

        }

        nonAlcoImage.setOnClickListener {
            if(requireActivity().application.isOnline()) {
                cocktailViewModel.setAlcoholicDrinkFilter(false)
                if (cocktailViewModel.getNonAlcoholicDrinks().value.isNullOrEmpty()) cocktailViewModel.loadNonAlcoholicDrinks()
                this.findNavController().navigate(R.id.cocktailListFragment)
            }
            else Snackbar.make(requireView(), "There is no Internet my friend, not drinking today",Snackbar.LENGTH_LONG).show()

        }

        randomDrinkImage.setOnClickListener {
                if (requireActivity().application.isOnline()){
                    cocktailViewModel.loadRandomDrink()
                    cocktailViewModel.isRandomDrinkModeActive = true
                    this.findNavController().navigate(R.id.cocktailDetailFragment)
                } else Snackbar.make(requireView(), "There is no Internet my friend, not drinking today",Snackbar.LENGTH_LONG).show()
        }

        favouriteDrinksImage.setOnClickListener {
            this.findNavController().navigate(R.id.cocktailFavListFragment)
        }
    }


}


