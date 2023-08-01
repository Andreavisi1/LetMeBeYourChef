package com.example.letmebeyourchef.aggiungi_ingrediente

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.letmebeyourchef.R
import com.example.letmebeyourchef.adapters.IngredientiAdapter
import com.example.letmebeyourchef.adapters.IngredientiPreferitiAdapter
import com.example.letmebeyourchef.aggiungi_ingrediente.AggiungiIngredienteViewModel
import com.example.letmebeyourchef.databinding.FragmentIngredientiPreferitiBinding
import com.example.letmebeyourchef.databinding.FragmentPreferitiBinding
import com.example.letmebeyourchef.listeners.IngredientClickListener
import com.example.letmebeyourchef.listeners.IngredientDeleteClickListener
import com.example.letmebeyourchef.listeners.IngredientFavouriteClickListener
import com.example.letmebeyourchef.model.Pasto
import com.example.letmebeyourchef.recipeModels.Ingredient
import kotlinx.android.synthetic.main.add_delete_layout.*


class IngredientiPreferitiFragment : Fragment() {

    private lateinit var binding: FragmentIngredientiPreferitiBinding

    private lateinit var recyclerViewPreferiti: RecyclerView

    private val model = AggiungiIngredienteViewModel()

    private var  mContext: Context? = null



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_ingredienti_preferiti, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.getIngredientiPreferiti()
        recyclerViewPreferiti = binding.gridPreferiti
        recyclerViewPreferiti.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewPreferiti.setHasFixedSize(true)

        val dispensaObserver = Observer<List<Ingredient>>{
            val adapter = IngredientiPreferitiAdapter(requireContext(),
                model.preferitiLiveData.value!! as ArrayList<Ingredient>,
                ingredientClickListener, ingredientFavouriteClickListener)
            recyclerViewPreferiti.adapter = adapter

            //if (adapter.itemCount != 0) binding.dispensaTitle.text = "Your ingredients here"

        }

        model.preferitiLiveData.observe(viewLifecycleOwner,dispensaObserver)

    }

    private val ingredientClickListener: IngredientClickListener = object :
        IngredientClickListener {

        override fun onClickIngredient(
            id: Int,
            name: String?,
            image: String?
        ) {
            model.setIngredientiPossedutiOnDB(
                id, name, image, requireContext())
        }
    }

    private val ingredientFavouriteClickListener: IngredientFavouriteClickListener = object :
        IngredientFavouriteClickListener {
        override fun onClickFavouriteIngredient(
            id: Int,
            name: String?,
            image: String?
        ) {
            model.setIngredientiPreferitiOnDB(
                id,
                name,
                image,
                requireContext()
            )
        }
    }

    private val ingredientDeleteClickListener: IngredientDeleteClickListener = object :
        IngredientDeleteClickListener {
        override fun onClickDeleteIngredient(
            id: Int,
            name: String?,
            image: String?
        ) {
            model.removeIngredient(
                id,
                requireContext()
            )
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        super.onAttach(requireActivity())
        mContext = context
    }

    override fun onDetach() {
        super.onDetach()
        mContext = null
    }


}