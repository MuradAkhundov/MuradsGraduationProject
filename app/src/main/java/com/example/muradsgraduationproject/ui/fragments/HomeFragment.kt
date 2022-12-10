package com.example.muradsgraduationproject.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.muradsgraduationproject.R
import com.example.muradsgraduationproject.data.entity.Foods
import com.example.muradsgraduationproject.databinding.FragmentHomeBinding
import com.example.muradsgraduationproject.ui.adapter.FoodAdapter
import com.example.muradsgraduationproject.ui.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
private lateinit var binding: FragmentHomeBinding
private lateinit var viewModel: HomeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)

        binding.homeFragment = this
        binding.toolbarHome.title = "                           Our Menu "

        viewModel.foodsList.observe(viewLifecycleOwner){
            val adapter = FoodAdapter(requireContext(),it.foods)
            binding.adapter = adapter
        }
        //adapter
//        binding.rv.layoutManager = LinearLayoutManager(requireContext())
 binding.rv.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)



        // Inflate the layout for this fragment
        return binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        binding.adapter?.onCardClick = {
//            Log.e("aa","test")
//
//            findNavController(view).navigate(HomeFragmentDirections.toDetail(it))
////            findNavController().navigate(R.id.toDetail)
//
//        }
//    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: HomeViewModel by viewModels()
        viewModel = tempViewModel
    }
}