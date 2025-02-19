package com.example.cocktailtaskapp.home.vm

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cocktailtaskapp.R
import com.example.cocktailtaskapp.home.model.DrinkModel

class CocktailAdapter(
    private var cocktailList: List<DrinkModel?>
) : RecyclerView.Adapter<CocktailAdapter.CocktailViewHolder>() {

    class CocktailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun updateUI(drink: DrinkModel?) {
            itemView.findViewById<TextView>(R.id.cocktailTag).text = drink?.strDrink ?: "No Name"
            itemView.findViewById<TextView>(R.id.cocktailInstruction).text = drink?.strInstructions ?: "No Instructions"
            itemView.findViewById<TextView>(R.id.cocktailCategory).text = drink?.strCategory ?: "No Category"

            val imageView = itemView.findViewById<ImageView>(R.id.cocktailImage)
            Glide.with(itemView).load(drink?.strDrinkThumb).into(imageView)
            imageView.contentDescription = "Image of ${drink?.strTags ?: "Cocktail"}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cocktail_card, parent, false)
        return CocktailViewHolder(view)
    }

    override fun getItemCount() = cocktailList.size

    override fun onBindViewHolder(holder: CocktailViewHolder, position: Int) {
        holder.updateUI(cocktailList[position])
    }

    // Function to update adapter data dynamically
    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newList: List<DrinkModel?>) {
        cocktailList = newList
        notifyDataSetChanged()
    }
}

