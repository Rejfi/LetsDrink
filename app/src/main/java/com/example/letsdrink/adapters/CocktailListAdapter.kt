package com.example.letsdrink.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.letsdrink.R
import com.example.letsdrink.data.Drink
import com.squareup.picasso.Picasso

class CocktailListAdapter(private val drinkList: List<Drink>,
                          private val onDrinkClickListener: OnDrinkClickListener)
    : RecyclerView.Adapter<CocktailListAdapter.CocktailViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(R.layout.cocktail_row, parent, false)
    return CocktailViewHolder(row)
}

    override fun getItemCount(): Int {
        return drinkList.size
    }

    override fun onBindViewHolder(holder: CocktailViewHolder, position: Int) {
        Picasso.get().load(drinkList[position].strDrinkThumb).into(holder.cocktailImage)
        holder.cocktailName.text = drinkList[position].strDrink
    }

    inner class CocktailViewHolder(view: View): RecyclerView.ViewHolder(view) {
        init {
            itemView.setOnClickListener{
                onDrinkClickListener.onDrinkClick(adapterPosition, drinkList[adapterPosition].idDrink!!, getItemAtPosition(adapterPosition) )
            }
        }

        val cocktailImage: ImageView = itemView.findViewById(R.id.drinkListImage)
        val cocktailName: TextView = itemView.findViewById(R.id.drinkListName)
    }


    fun getItemAtPosition(position: Int):Drink{
        return drinkList[position]
    }
}

interface OnDrinkClickListener{
    fun onDrinkClick(position: Int, id: String, drink: Drink)
}

