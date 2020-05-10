package com.example.letsdrink.ui.fragments

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.letsdrink.R
import com.example.letsdrink.data.Drink
import com.example.letsdrink.data.Ingredient
import com.example.letsdrink.viewmodels.CocktailViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_cocktail_detail.*

class CocktailDetailFragment : Fragment() {

    private lateinit var cocktailViewModel: CocktailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cocktail_detail, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cocktailViewModel = ViewModelProvider(requireActivity())[CocktailViewModel::class.java]
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if(cocktailViewModel.isRandomDrinkModeActive){
            cocktailViewModel.getRandomDrink().observe(viewLifecycleOwner, Observer {
                Picasso.get().load(it[0].strDrinkThumb).into(detailFragImage)
                detailFragDrinkName.text = it[0].strDrink
                val listOfIngredients = getListIngredients(it)
                for(i in listOfIngredients){
                    val ingConcatenate = "${i.ingrName} - ${i.ingrMeasure} \n"
                    detailFragIngredientTextView.append(ingConcatenate)
                }
                instructionTextView.text = it[0].strInstructions
                cocktailViewModel.setSelectedDrink(it)
            })

        }
        else{
            cocktailViewModel.getSelectedDrink().observe(viewLifecycleOwner, Observer {
                Picasso.get().load(it[0].strDrinkThumb).into(detailFragImage)
                detailFragDrinkName.text = it[0].strDrink
                val listOfIngredients = getListIngredients(it)
                for(i in listOfIngredients){
                    val ingConcatenate = "${i.ingrName} - ${i.ingrMeasure} \n"
                    detailFragIngredientTextView.append(ingConcatenate)
                }
                instructionTextView.text = it[0].strInstructions
            })
        }

        favouriteFloatBT.setOnClickListener{
            cocktailViewModel.insertFavDrink(cocktailViewModel.getSelectedDrink().value!![0])
            cocktailViewModel.loadFavouriteDrinks()
        }

    }


    //Function helps with parse ingredients
    private fun getListIngredients(list: List<Drink>): List<Ingredient>{
        val arrayIngredients = ArrayList<Ingredient>()
        if(list[0].strIngredient1 != null && list[0].strMeasure1 != null){
            val ingr = Ingredient(list[0].strIngredient1, list[0].strMeasure1)
            arrayIngredients.add(ingr)
        }
        if(list[0].strIngredient2 != null && list[0].strMeasure2 != null){
            val ingr = Ingredient(list[0].strIngredient2, list[0].strMeasure2)
            arrayIngredients.add(ingr)
        }
        if(list[0].strIngredient3 != null && list[0].strMeasure3 != null){
            val ingr = Ingredient(list[0].strIngredient3, list[0].strMeasure3)
            arrayIngredients.add(ingr)
        }
        if(list[0].strIngredient4 != null && list[0].strMeasure4 != null){
            val ingr = Ingredient(list[0].strIngredient4, list[0].strMeasure4)
            arrayIngredients.add(ingr)
        }
        if(list[0].strIngredient5 != null && list[0].strMeasure5 != null){
            val ingr = Ingredient(list[0].strIngredient5, list[0].strMeasure5)
            arrayIngredients.add(ingr)
        }
        if(list[0].strIngredient6 != null && list[0].strMeasure6 != null){
            val ingr = Ingredient(list[0].strIngredient6, list[0].strMeasure6)
            arrayIngredients.add(ingr)
        }
        if(list[0].strIngredient7 != null && list[0].strMeasure7 != null){
            val ingr = Ingredient(list[0].strIngredient7, list[0].strMeasure7)
            arrayIngredients.add(ingr)
        }
        if(list[0].strIngredient8 != null && list[0].strMeasure8 != null){
            val ingr = Ingredient(list[0].strIngredient8, list[0].strMeasure8)
            arrayIngredients.add(ingr)
        }
        if(list[0].strIngredient9 != null && list[0].strMeasure9 != null){
            val ingr = Ingredient(list[0].strIngredient9, list[0].strMeasure9)
            arrayIngredients.add(ingr)
        }
        if(list[0].strIngredient10 != null && list[0].strMeasure10 != null){
            val ingr = Ingredient(list[0].strIngredient10, list[0].strMeasure10)
            arrayIngredients.add(ingr)
        }
        if(list[0].strIngredient11 != null && list[0].strMeasure11 != null){
            val ingr = Ingredient(list[0].strIngredient11, list[0].strMeasure11)
            arrayIngredients.add(ingr)
        }
        if(list[0].strIngredient12 != null && list[0].strMeasure12 != null){
            val ingr = Ingredient(list[0].strIngredient12, list[0].strMeasure12)
            arrayIngredients.add(ingr)
        }
        if(list[0].strIngredient13 != null && list[0].strMeasure13 != null){
            val ingr = Ingredient(list[0].strIngredient13, list[0].strMeasure13)
            arrayIngredients.add(ingr)
        }
        if(list[0].strIngredient14 != null && list[0].strMeasure14 != null){
            val ingr = Ingredient(list[0].strIngredient14, list[0].strMeasure14)
            arrayIngredients.add(ingr)
        }
        if(list[0].strIngredient15 != null && list[0].strMeasure15 != null){
            val ingr = Ingredient(list[0].strIngredient15, list[0].strMeasure15)
            arrayIngredients.add(ingr)
        }

        return arrayIngredients.toList()
    }
}
