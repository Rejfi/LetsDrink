package com.example.letsdrink.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.letsdrink.R
import com.example.letsdrink.data.Drink
import com.squareup.picasso.Picasso

class CocktailFavouriteListAdapter(private val drinkList: MutableList<Drink>,
                                   private val onDrinkClickListener: OnDrinkClickListener)
    : RecyclerView.Adapter<CocktailFavouriteListAdapter.CocktailFavouriteListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailFavouriteListViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(R.layout.cocktail_row, parent, false)
        return CocktailFavouriteListViewHolder(row)
    }

    override fun getItemCount(): Int {
        return drinkList.size
    }

    override fun onBindViewHolder(holder: CocktailFavouriteListViewHolder, position: Int) {
        Picasso.get().load(drinkList[position].strDrinkThumb).into(holder.cocktailImage)
        holder.cocktailName.text = drinkList[position].strDrink
    }

    inner class CocktailFavouriteListViewHolder(view: View): RecyclerView.ViewHolder(view) {
        init {
            itemView.setOnClickListener{
                onDrinkClickListener.onDrinkClick(adapterPosition,
                    drinkList[adapterPosition].idDrink,
                    getItemAtPosition(adapterPosition) )
            }
        }

        val cocktailImage: ImageView = itemView.findViewById(R.id.drinkListImage)
        val cocktailName: TextView = itemView.findViewById(R.id.drinkListName)
    }

    fun getCurrentDrinkList(): List<Drink> {
        return drinkList.toList()
    }

    fun getItemAtPosition(position: Int):Drink{
        return drinkList[position]
    }

    fun deleteItemAtPosition(position: Int){
        drinkList.removeAt(position)
        notifyItemChanged(position)
    }
}

abstract class SwipeToDeleteCallback(context: Context) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    /*
    // Let's draw our delete view
    override fun onChildDraw(c: Canvas,
                             recyclerView: RecyclerView,
                             viewHolder: RecyclerView.ViewHolder,
                             dX: Float,
                             dY: Float,
                             actionState: Int,
                             isCurrentlyActive: Boolean) {

        val itemView = viewHolder.itemView
        val itemHeight = itemView.bottom - itemView.top

        // Draw the red delete background
        background.color = backgroundColor
        background.setBounds(
            itemView.right + dX.toInt(),
            itemView.top,
            itemView.right,
            itemView.bottom
        )
        background.draw(c)

        // Calculate position of delete icon
        val iconTop = itemView.top + (itemHeight - inHeight) / 2
        val iconMargin = (itemHeight - inHeight) / 2
        val iconLeft = itemView.right - iconMargin - inWidth
        val iconRight = itemView.right - iconMargin
        val iconBottom = iconTop + inHeight

        // Draw the delete icon
        icon.setBounds(iconLeft, iconTop, iconRight, iconBottom)
        icon.draw(c)

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

     */
}

