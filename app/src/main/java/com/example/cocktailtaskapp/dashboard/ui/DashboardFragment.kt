package com.example.cocktailtaskapp.dashboard.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.cocktailtaskapp.databinding.FragmentDashboardBinding
import com.example.cocktailtaskapp.dashboard.vm.DashboardViewModel



class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //  Initialize ViewModel
        dashboardViewModel = ViewModelProvider(this)[DashboardViewModel::class.java]

        //  Observe LiveData to update UI when data changes
        dashboardViewModel.randomCocktail.observe(viewLifecycleOwner) { cocktailList ->
            if (!cocktailList.isNullOrEmpty()) {
                val cocktail = cocktailList.first()  // Take the first random cocktail

                binding.drinkName.text = cocktail?.strDrink ?: "Unknown Drink"
                binding.drinkTag.text = cocktail?.strTags ?: "No Tags"
                binding.drinkCategory.text = cocktail?.strCategory ?: "No Category"
                binding.drinkInstruction.text = cocktail?.strInstructions ?: "No Instructions"
                binding.drinkDateModified.text = cocktail?.dateModified ?: "No Date"

                Glide.with(requireContext())
                    .load(cocktail?.strDrinkThumb)
                    .into(binding.drinkImage)
            }
        }

        //Set OnClickListener for Refresh Button
        binding.btnRefresh.setOnClickListener {
            dashboardViewModel.getRandomCocktailData()
        }

        // Fetch initial cocktail data
        dashboardViewModel.getRandomCocktailData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}