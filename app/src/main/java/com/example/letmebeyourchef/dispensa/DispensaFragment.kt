package com.example.letmebeyourchef.dispensa

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.letmebeyourchef.databinding.FragmentDispensaBinding
import com.example.letmebeyourchef.R
import com.example.letmebeyourchef.adapters.IngredientAdapter


class DispensaFragment : Fragment() {


    private var _binding: FragmentDispensaBinding? = null
    private val binding get() = _binding!!

    private val ingredientViewModel: DispensaViewModel by viewModels()

    private val ingredientAdapter: IngredientAdapter by lazy {
        IngredientAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDispensaBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = ingredientViewModel

        binding.ingredientsRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = ingredientAdapter
        }
        ingredientViewModel.ingredientsLiveData.observe(
            viewLifecycleOwner,
            Observer { ingredients ->
                ingredientAdapter.submitList(ingredients)
            })

        ingredientViewModel.fetchIngredients()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
