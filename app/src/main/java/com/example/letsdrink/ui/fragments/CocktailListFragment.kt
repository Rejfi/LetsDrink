package com.example.letsdrink.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.letsdrink.R
import com.example.letsdrink.adapters.CocktailListAdapter
import com.example.letsdrink.adapters.OnDrinkClickListener
import com.example.letsdrink.viewmodels.CocktailViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.cocktail_row.*
import kotlinx.android.synthetic.main.fragment_cocktail_detail.*
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
        Log.e("Tag", "FragmentList sie wykonalo")
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
            if(cocktailViewModel.getAlcoholicDrinksFilter()){
                cocktailViewModel.getAlcoholicDrinks().observe(viewLifecycleOwner, Observer {
                    val adapter = CocktailListAdapter(it, this)
                    recyclerView.adapter = adapter
                })
            }else{
                cocktailViewModel.getNonAlcoholicDrinks().observe(viewLifecycleOwner, Observer {
                    val adapter = CocktailListAdapter(it, this)
                    recyclerView.adapter = adapter
                })
            }

    }

    override fun onDrinkClick(position: Int, id: String) {
        Log.d("Tag", "Klikniete ID drinku: $id")
        cocktailViewModel.setDrinkById(id)
        this.findNavController().navigate(R.id.cocktailDetailFragment)
        /*
        requireActivity().supportFragmentManager.beginTransaction().apply {
            val cocktailDetailFragment = CocktailDetailFragment()
            replace(R.id.fragment_container, cocktailDetailFragment, "CocktailDetailFragment")
            addToBackStack("CocktailDetailFragmentStack")
            commit()
        }

         */
    }

}
