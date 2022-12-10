package com.example.muradsgraduationproject.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.muradsgraduationproject.R
import com.example.muradsgraduationproject.data.entity.FoodsCart
import com.example.muradsgraduationproject.databinding.FragmentDetailBinding
import com.example.muradsgraduationproject.ui.viewmodel.DetailViewModel
import com.example.muradsgraduationproject.ui.viewmodel.HomeViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    lateinit var viewModel: DetailViewModel
    private lateinit var foodsCart: FoodsCart
    var orderAmount = 0
    var quantity = 1
    var isListFull = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        // Inflate the layout for this fragment
        binding.detailFragment = this
        binding.toolbarDetail.title = "Food Detail"


        binding.button.setOnClickListener {

                quantity -= 1
                binding.textViewOrderAmount.text = quantity.toString()

        }

        binding.button2.setOnClickListener {
            quantity += 1
            binding.textViewOrderAmount.text = quantity.toString()
        }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        quantity = 1

        val bundle: DetailFragmentArgs by navArgs()
        val resultFood = bundle.food

        Glide.with(requireContext())
            .load("http://kasimadalan.pe.hu/foods/images/${resultFood.image}")
            .into(binding.imageViewDetail)
        binding.textViewName.text = resultFood.name
        binding.textViewPrice.text = "${resultFood.price}  ₼"
        binding.textViewCategory.text = resultFood.category
        viewModel.getAllFoodsCart()

        viewModel.foodsCartList.observe(viewLifecycleOwner) {
            isListFull = !it.isNullOrEmpty()
        }
        binding.buttonGoToCart.setOnClickListener {

            viewModel.foodsCartList.removeObservers(viewLifecycleOwner)
//            viewModel.itemIsAdded.removeObservers(viewLifecycleOwner)

            val transition = DetailFragmentDirections.toCart()
            Navigation.findNavController(it).navigate(transition)
        }

        binding.imageViewCart.setOnClickListener {
            orderAmount = 0
            if (isListFull) {
                viewModel.foodsCartList.value?.forEach { item ->
                    if (item.name == resultFood.name) {
                        orderAmount += item.orderAmount
                        viewModel.delete(item.cartId, "Murad")
                    }
                }

                foodsCart = FoodsCart(
                    cartId = 0,
                    name = resultFood.name,
                    image = resultFood.image,
                    price = resultFood.price * (orderAmount + quantity),
                    category = resultFood.category,
                    orderAmount = orderAmount + quantity,
                    userName = "Murad"
                )
            } else {
                foodsCart = FoodsCart(
                    cartId = 0,
                    name = resultFood.name,
                    image = resultFood.image,
                    price = resultFood.price * quantity,
                    category = resultFood.category,
                    orderAmount = quantity,
                    userName = "Murad"
                )
            }
            viewModel.insert(foodsCart)
            Snackbar.make(view, "Item is added to your Cart!!!", Snackbar.LENGTH_SHORT).show()

            Log.e("Food Add", "get cards")

        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: DetailViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onPause() {
        super.onPause()

    }

}