package com.example.letsdrink.ui.fragments

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.letsdrink.R
import com.example.letsdrink.adapters.CocktailListAdapter
import com.example.letsdrink.adapters.OnDrinkClickListener
import com.example.letsdrink.data.Drink
import com.example.letsdrink.isOnline
import com.example.letsdrink.viewmodels.CocktailViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_cocktail_list.*

/**
 * A simple [Fragment] subclass.
 */
class CocktailListFragment : Fragment(), OnDrinkClickListener {

    private lateinit var cocktailViewModel: CocktailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cocktail_list, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cocktailViewModel = ViewModelProvider(requireActivity())[CocktailViewModel::class.java]
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        cocktailViewModel.isRandomDrinkModeActive = false

        if(cocktailViewModel.getAlcoholicDrinksFilter()){
                cocktailViewModel.getAlcoholicDrinks().observe(viewLifecycleOwner, Observer {
                    if(!it.isNullOrEmpty()){
                        val adapter = CocktailListAdapter(it, this)
                        recyclerView.adapter = adapter
                    } else Snackbar.make(requireView(), "Not internet - not drinking. Srrrrry", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Retry"){ cocktailViewModel.getAlcoholicDrinks() }
                        .show()

                })
            }else{
                cocktailViewModel.getNonAlcoholicDrinks().observe(viewLifecycleOwner, Observer {
                    if(!it.isNullOrEmpty()){
                        val adapter = CocktailListAdapter(it, this)
                        recyclerView.adapter = adapter
                    }else Snackbar.make(requireView(), "Not internet - not drinking. Srrrrry", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Retry"){ cocktailViewModel.getNonAlcoholicDrinks() }
                        .show()

                })
            }
    }

    override fun onDrinkClick(position: Int, id: String, drink: Drink) {
        if(requireActivity().application.isOnline()){
            cocktailViewModel.loadDrinkById(id)
            this.findNavController().navigate(R.id.cocktailDetailFragment)
        } else Snackbar.make(requireView(), "Not internet - not drinking. Srrrrry", Snackbar.LENGTH_LONG).show()

    }

}
