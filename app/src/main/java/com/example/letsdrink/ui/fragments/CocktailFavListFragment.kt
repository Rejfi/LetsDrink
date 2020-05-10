package com.example.letsdrink.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.letsdrink.R
import com.example.letsdrink.adapters.CocktailFavouriteListAdapter
import com.example.letsdrink.adapters.CocktailListAdapter
import com.example.letsdrink.adapters.OnDrinkClickListener
import com.example.letsdrink.adapters.SwipeToDeleteCallback
import com.example.letsdrink.data.Drink
import com.example.letsdrink.viewmodels.CocktailViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_cocktail_fav_list.*
import kotlinx.android.synthetic.main.fragment_cocktail_list.*
import kotlinx.coroutines.flow.asFlow

class CocktailFavListFragment : Fragment(), OnDrinkClickListener {

    private lateinit var cocktailViewModel: CocktailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cocktail_fav_list, container, false)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cocktailViewModel = ViewModelProvider(requireActivity())[CocktailViewModel::class.java]

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerViewFavListFrag.layoutManager = LinearLayoutManager(requireContext())


        cocktailViewModel.getAllFavDrinks().observe(viewLifecycleOwner, Observer{
                if (!it.isNullOrEmpty()) {
                    val adapter = CocktailFavouriteListAdapter(it.toMutableList(), this)
                    recyclerViewFavListFrag.adapter = adapter

                    val swipeHandler = object : SwipeToDeleteCallback(requireContext()) {
                        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                            if(!it.isNullOrEmpty()){
                                adapter.deleteItemAtPosition(viewHolder.adapterPosition)
                                cocktailViewModel.deleteFavDrink(it[viewHolder.adapterPosition])
                                cocktailViewModel.setFavouriteDrinks(adapter.getCurrentDrinkList())
                            }
                        }
                }
                    val itemTouchHelper = ItemTouchHelper(swipeHandler)
                    itemTouchHelper.attachToRecyclerView(recyclerViewFavListFrag)
                }
            })
    }

    override fun onDrinkClick(position: Int, id: String, drink: Drink) {
           cocktailViewModel.setSelectedDrink(listOf(drink))
            this.findNavController().navigate(R.id.cocktailDetailFragment)
    }
}
