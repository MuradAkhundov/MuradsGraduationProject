package com.example.muradsgraduationproject.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.muradsgraduationproject.R
import com.example.muradsgraduationproject.data.entity.Foods
import com.example.muradsgraduationproject.databinding.CardDesignBinding
import com.example.muradsgraduationproject.databinding.FragmentCartBinding
import com.example.muradsgraduationproject.ui.fragments.HomeFragment
import com.example.muradsgraduationproject.ui.fragments.HomeFragmentDirections
import java.math.MathContext

class FoodAdapter(var mContext: Context, var foodList: List<Foods>) :
    RecyclerView.Adapter<FoodAdapter.CardDesignHolder>() {

    inner class CardDesignHolder(var binding: CardDesignBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignHolder {
        val binding = CardDesignBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return CardDesignHolder(binding)
    }

    override fun onBindViewHolder(holder: CardDesignHolder, position: Int) {
        val food = foodList.get(position)
        val b = holder.binding


        val url = "http://kasimadalan.pe.hu/foods/images/${food.image}"
        Glide.with(mContext).load(url).override(500,500).into(b.imageViewFood)


        b.cardViewFood.setOnClickListener{
       val transition = HomeFragmentDirections.toDetail(food = food)
            Navigation.findNavController(it).navigate(transition)

        }
        b.textViewNameCard.text = "${food.name}"
        b.textViewPriceCard.text = "${food.price} ₼"
//        b.textViewNameCard.text = "${food.name}"
//        b.textViewCategoryCard.text = "${food.category}"
    }

    override fun getItemCount(): Int {
        return foodList.size
    }
}