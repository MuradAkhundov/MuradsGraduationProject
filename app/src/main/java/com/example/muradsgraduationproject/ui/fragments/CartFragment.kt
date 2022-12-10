package com.example.muradsgraduationproject.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.muradsgraduationproject.R
import com.example.muradsgraduationproject.data.FoodsCartResponse
import com.example.muradsgraduationproject.data.entity.FoodsCart
import com.example.muradsgraduationproject.databinding.FragmentCartBinding
import com.example.muradsgraduationproject.ui.adapter.FoodAdapter
import com.example.muradsgraduationproject.ui.adapter.FoodCartAdapter
import com.example.muradsgraduationproject.ui.viewmodel.CartViewModel
import com.example.muradsgraduationproject.ui.viewmodel.HomeViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {

private lateinit var binding: FragmentCartBinding
    private lateinit var viewModel: CartViewModel
    private  var list = listOf<FoodsCart>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        val bundle :CartFragmentArgs by navArgs()
//        val resultFood = bundle.foodCart
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(inflater,container,false)

        binding.cartFragment = this

binding.toolbarCart.title = "                                Cart"

//        Glide.with(requireContext()).load( "http://kasimadalan.pe.hu/foods/images/${resultFood.image}").into(binding.ca)
//        binding.textViewName.setText(resultFood.name)
//        binding.textViewPrice.setText(resultFood.price.toString())
//        binding.textViewCategory.setText(resultFood.category)
        viewModel.foodsCartList.observe(viewLifecycleOwner){
            try {
                if (it != null ) {
                    println("a")
                    list = it
                    val adapter = FoodCartAdapter(requireContext(), list,viewModel)
                    binding.adapter = adapter
                    binding.rvCart.layoutManager = LinearLayoutManager(requireContext())
                }
            }
            catch (e :java.lang.Exception){

            }
        }

//        binding.rvCart.layoutManager = LinearLayoutManager(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: CartViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllFoodsCart()
    }

}