package com.example.cocktailtaskapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cocktailtaskapp.databinding.FragmentHomeBinding
import com.example.cocktailtaskapp.utils.UiStatus
import com.example.cocktailtaskapp.vm.CocktailAdapter
import com.example.cocktailtaskapp.vm.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var cocktailAdapter: CocktailAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //Hides app bar
        (activity as AppCompatActivity).supportActionBar?.hide()


        // Initialize RecyclerView
        setupRecyclerView()

        // Observe ViewModel Data and Update UI
        homeViewModel.cocktailList.observe(viewLifecycleOwner) { cocktailList ->
            cocktailAdapter.updateData(cocktailList)
        }

        // Fetch Cocktail Data from API
         homeViewModel.getCocktailData()


        // Fetch cocktails from Room Database
        homeViewModel.getCocktailsFromDB()


//        // Observe data from Room
       homeViewModel.cocktailsFromDb.observe(viewLifecycleOwner) { status ->
          when (status) {
               is UiStatus.LOADING -> {
                   // Show loading state
              }
               is UiStatus.SUCCESS -> {
                   val cocktails = status.data
                   // Update RecyclerView or UI with cocktails
                    Log.e("Room Data", "$cocktails")
               }
                is UiStatus.ERROR -> {
                   Log.e("RoomDB", "Error fetching from Room: ${status.message}")
               }
            }
       }


        return root
    }

    private fun setupRecyclerView() {
        cocktailAdapter = CocktailAdapter(emptyList()) // Initialize with empty list

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = cocktailAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
