package com.example.muradsgraduationproject.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.muradsgraduationproject.data.entity.Foods
import com.example.muradsgraduationproject.data.entity.FoodsCart
import com.example.muradsgraduationproject.databinding.CardDesignBinding
import com.example.muradsgraduationproject.databinding.CardDesignCartBinding
import com.example.muradsgraduationproject.ui.viewmodel.CartViewModel
import com.example.muradsgraduationproject.ui.viewmodel.HomeViewModel
import com.google.android.material.snackbar.Snackbar

class FoodCartAdapter(var mContext: Context, var foodCartList: List<FoodsCart>,var viewModel: CartViewModel):
    RecyclerView.Adapter<FoodCartAdapter.CardDesignHolder>() {

//    private lateinit var viewModel: CartViewModel

    inner class CardDesignHolder(var binding: CardDesignCartBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignHolder {
        val binding = CardDesignCartBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return CardDesignHolder(binding)
    }

    override fun onBindViewHolder(holder: CardDesignHolder, position: Int) {
      val foodCart = foodCartList.get(position)
       val b = holder.binding


     val url = "http://kasimadalan.pe.hu/foods/images/${foodCart.image}"
        Glide.with(mContext).load(url).override(400,400).into(b.imageViewFoodCart)



        b.textViewPriceCart.text = "Price : ${foodCart.price}  ₼"
        b.textViewAmountCart.text = "Amount : ${foodCart.orderAmount}"
        b.textViewNameCart.text = "${foodCart.name}"
//        b.textViewCategoryCart.text = "${foodCart.category}"


        b.imageView.setOnClickListener {
            Snackbar.make(it,"Do you want to delete ${foodCart.name} ?", Snackbar.LENGTH_LONG)
                .setAction("YES"){
                    viewModel.delete(foodCart.cartId,foodCart.userName)
                }.show()
        }
    }

    override fun getItemCount(): Int {
       return foodCartList.size
    }


}