package com.example.letsdrink.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.letsdrink.R
import com.example.letsdrink.viewmodels.CocktailViewModel
import kotlinx.android.synthetic.main.fragment_criteria.*

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
            cocktailViewModel.setAlcoholicDrinkFilter(true)
            requireActivity().supportFragmentManager.beginTransaction().apply {
                val cocktailListFragment = CocktailListFragment()
                replace(R.id.fragment_container,cocktailListFragment, "CocktailListFragment")
                addToBackStack("CocktailListFragmentStack")
                commit()
            }
        }

        nonAlcoImage.setOnClickListener {
            cocktailViewModel.setAlcoholicDrinkFilter(false)
            requireActivity().supportFragmentManager.beginTransaction().apply {
                val cocktailListFragment = CocktailListFragment()
                replace(R.id.fragment_container,cocktailListFragment, "CocktailListFragment")
                addToBackStack("CocktailListFragmentStack")
                commit()
            }

        }

    }
}

