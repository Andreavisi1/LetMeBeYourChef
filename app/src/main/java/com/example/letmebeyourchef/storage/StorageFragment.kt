package com.example.letmebeyourchef.storage

import android.content.Context
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.letmebeyourchef.R
import com.example.letmebeyourchef.adapters.IngredientiAdapter
import com.example.letmebeyourchef.aggiungi_ingrediente.AggiungiIngredienteActivity
import com.example.letmebeyourchef.databinding.FragmentStorageBinding
import com.example.letmebeyourchef.listeners.IngredientClickListener
import com.example.letmebeyourchef.listeners.IngredientDeleteClickListener
import com.example.letmebeyourchef.listeners.IngredientFavouriteClickListener
import com.example.letmebeyourchef.recipeModels.ExtendedIngredient
import com.example.letmebeyourchef.recipeModels.Ingredient
import com.example.letmebeyourchef.ricetta.ActivityDettagliRicetta


class StorageFragment : Fragment() {

        private lateinit var binding: FragmentStorageBinding
        private val model = StorageViewModel()

        private var  mContext: Context? = null

        private lateinit var recyclerViewDispensa: RecyclerView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_storage, container, false)
            //aggiornamento automatico view
            binding.viewModel = model
            binding.lifecycleOwner = this

            return binding.root

        }


        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            model.getIngredientiPosseduti()
            recyclerViewDispensa = binding.recyclerDispensa
            recyclerViewDispensa.layoutManager = GridLayoutManager(mContext, 1)
            recyclerViewDispensa.setHasFixedSize(true)

            val dispensaObserver = Observer<List<Ingredient>>{
                val adapter = IngredientiAdapter(requireContext(),
                    model.ingredientiPossedutiLiveData.value!! as ArrayList<Ingredient>,
                    ingredientClickListener, ingredientFavouriteClickListener)
                recyclerViewDispensa.adapter = adapter

                if (adapter.itemCount != 0) binding.dispensaTitle.text = "Your ingredients here"

            }

            model.ingredientiPossedutiLiveData.observe(viewLifecycleOwner,dispensaObserver)

            binding.btnAddIngredients.setOnClickListener {
                val intent = Intent(requireActivity(), AggiungiIngredienteActivity::class.java)
                startActivity(intent)
            }

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


        private fun startAnimation(glass : ImageView){
            glass.setBackgroundResource(R.drawable.filling_animation)
            val frameAnimation: AnimationDrawable = glass.background as AnimationDrawable
            frameAnimation.start()
        }

        private fun vibrate(){
            val vibrator = requireContext().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            val vibrationEffect1 = VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE)
            vibrator.cancel()
            vibrator.vibrate(vibrationEffect1)
        }


    }
