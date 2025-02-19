package com.example.cocktailtaskapp.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cocktailtaskapp.databinding.FragmentHomeBinding
import com.example.cocktailtaskapp.home.vm.CocktailAdapter
import com.example.cocktailtaskapp.home.vm.HomeViewModel

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
