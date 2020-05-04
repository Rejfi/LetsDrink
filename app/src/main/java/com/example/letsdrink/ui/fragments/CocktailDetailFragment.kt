package com.example.letsdrink.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.letsdrink.R
import com.example.letsdrink.data.Drink
import com.example.letsdrink.data.Ingredient
import com.example.letsdrink.viewmodels.CocktailViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_cocktail_detail.*
/**
{
    "drinks": [
    {
        "idDrink": "11007",
        "strDrink": "Margarita",
        "strTags": "IBA,ContemporaryClassic",
        "strCategory": "Ordinary Drink",
        "strIBA": "Contemporary Classics",
        "strAlcoholic": "Alcoholic",
        "strGlass": "Cocktail glass",
        "strInstructions": "Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten only the outer rim and sprinkle the salt on it. The salt should present to the lips of the imbiber and never mix into the cocktail. Shake the other ingredients with ice, then carefully pour into the glass.",
        "strDrinkThumb": "https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg",
        "strIngredient1": "Tequila",
        "strIngredient2": "Triple sec",
        "strIngredient3": "Lime juice",
        "strIngredient4": "Salt",
        "strIngredient5": null,
        "strIngredient6": null,
        "strIngredient7": null,
        "strIngredient8": null,
        "strIngredient9": null,
        "strIngredient10": null,
        "strIngredient11": null,
        "strIngredient12": null,
        "strIngredient13": null,
        "strIngredient14": null,
        "strIngredient15": null,
        "strMeasure1": "1 1/2 oz ",
        "strMeasure2": "1/2 oz ",
        "strMeasure3": "1 oz ",
        "strMeasure4": null,
        "strMeasure5": null,
        "strMeasure6": null,
        "strMeasure7": null,
        "strMeasure8": null,
        "strMeasure9": null,
        "strMeasure10": null,
        "strMeasure11": null,
        "strMeasure12": null,
        "strMeasure13": null,
        "strMeasure14": null,
        "strMeasure15": null,
    }
    ]
}
*/

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

        cocktailViewModel.getSelectedDrink().observe(viewLifecycleOwner, Observer {
           Picasso.get().load(it[0].strDrinkThumb).into(detailFragImage)
            detailFragDrinkName.text = it[0].strDrink
            val listOfIngredients = getListIngredients(it)
            detailFragCardView.removeAllViews()
            for(i in listOfIngredients){
                val ingredientTextView = TextView(requireContext())
                val ingConcatenate = "${i.ingrName} - ${i.ingrMeasure}"
                ingredientTextView.textSize = 18f
                ingredientTextView.text = ingConcatenate
                detailFragCardView.addView(ingredientTextView)
            }
            instructionTextView.text = it[0].strInstructions
        })
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
