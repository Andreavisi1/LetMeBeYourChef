package com.example.letmebeyourchef.aggiungi_ingrediente

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.letmebeyourchef.R
import com.example.letmebeyourchef.adapters.IngredientiPreferitiAdapter
import com.example.letmebeyourchef.databinding.FragmentIngredientiPreferitiBinding
import com.example.letmebeyourchef.listeners.IngredientClickListener
import com.example.letmebeyourchef.listeners.IngredientDeleteClickListener
import com.example.letmebeyourchef.listeners.IngredientFavouriteClickListener
import com.example.letmebeyourchef.recipeModels.Ingredient


class IngredientiPreferitiFragment : Fragment() {

    var dialog: ProgressDialog? = null

    private lateinit var binding: FragmentIngredientiPreferitiBinding

    private lateinit var recyclerViewPreferiti: RecyclerView

    private val model = AggiungiIngredienteViewModel()

    private var  mContext: Context? = null



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_ingredienti_preferiti, container, false)

        dialog = ProgressDialog(requireContext())
        dialog!!.setTitle("Ohh... good tastes!")
        dialog!!.show()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.getIngredientiPreferiti()
        recyclerViewPreferiti = binding.gridPreferiti
        recyclerViewPreferiti.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewPreferiti.setHasFixedSize(true)

        binding.dispensaTitle21.movementMethod = ScrollingMovementMethod()

        val dispensaObserver = Observer<List<Ingredient>>{
            val adapter = IngredientiPreferitiAdapter(requireContext(),
                model.preferitiLiveData.value!! as ArrayList<Ingredient>,
                ingredientClickListener, ingredientFavouriteClickListener, ingredientDeleteClickListener)
            recyclerViewPreferiti.adapter = adapter

            //if (adapter.itemCount != 0) binding.dispensaTitle.text = "Your ingredients here"

            dialog!!.dismiss()

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