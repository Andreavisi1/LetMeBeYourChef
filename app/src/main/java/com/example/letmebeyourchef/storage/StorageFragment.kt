package com.example.letmebeyourchef.storage

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.letmebeyourchef.R
import com.example.letmebeyourchef.adapters.StorageAdapter
import com.example.letmebeyourchef.aggiungi_ingrediente.AggiungiIngredienteActivity
import com.example.letmebeyourchef.chef.ChefActivity
import com.example.letmebeyourchef.databinding.FragmentStorageBinding
import com.example.letmebeyourchef.listeners.AddToCartIngredientClickListener
import com.example.letmebeyourchef.listeners.IngredientDeleteClickListener
import com.example.letmebeyourchef.listeners.IngredientFavouriteClickListener
import com.example.letmebeyourchef.recipeModels.Ingredient


class StorageFragment : Fragment() {

        var dialog: ProgressDialog? = null
        private lateinit var binding: FragmentStorageBinding
        private val model = StorageViewModel()

        private var  mContext: Context? = null

        private lateinit var recyclerViewDispensa: RecyclerView
        private var ingredientiPossedutiFinali: String = ""


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_storage, container, false)
            //aggiornamento automatico view
            binding.viewModel = model
            binding.lifecycleOwner = this

            dialog = ProgressDialog(requireContext())
            dialog!!.setTitle("Let's see if you went shopping...")
            dialog!!.show()

            return binding.root

        }


        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            model.getIngredientiPosseduti()
            recyclerViewDispensa = binding.recyclerDispensa
            recyclerViewDispensa.layoutManager = GridLayoutManager(mContext, 1)
            recyclerViewDispensa.setHasFixedSize(true)

            val dispensaObserver = Observer<List<Ingredient>>{
                val adapter = StorageAdapter(requireContext(),
                    model.ingredientiPossedutiLiveData.value!! as ArrayList<Ingredient>,
                    addToCartIngredientClickListener,/* ingredientFavouriteClickListener, */ingredientDeleteClickListener)
                recyclerViewDispensa.adapter = adapter

                //if (adapter.itemCount != 0) binding.dispensaTitle.text = "Your ingredients here"

                dialog!!.dismiss()

            }

            binding.dispensaTitle.movementMethod = ScrollingMovementMethod()
            binding.dispensaTitle21.movementMethod = ScrollingMovementMethod()


            model.ingredientiPossedutiLiveData.observe(viewLifecycleOwner,dispensaObserver)



            binding.btnAddIngredients.setOnClickListener {
                val intent = Intent(requireActivity(), AggiungiIngredienteActivity::class.java)
                startActivity(intent)
            }

            binding.searchBtn.setOnClickListener {

                val intent = Intent(requireActivity(), ChefActivity::class.java)

                Log.e(model.ingredientiPossedutiLiveData.value!!.size.toString(), "SIZE")
                Log.e(model.ingredientiPossedutiLiveData.value!!.toString(), "VALUE")
                Log.e(model.ingredientiPossedutiLiveData.toString(), "INGREDIENTI POSSEDUTI")

                ingredientiPossedutiFinali += "salt"

                for (i in 0..model.ingredientiPossedutiLiveData.value!!.size-1) {

                    ingredientiPossedutiFinali += ","
                    ingredientiPossedutiFinali += model.ingredientiPossedutiLiveData.value!![i].name
                    //if (model.ingredientiPossedutiLiveData.value!![i+1]!=null)
                        //ingredientiPossedutiFinali += ","
                    Log.e(ingredientiPossedutiFinali.toString(), "INGREDIENTI POSSEDUTI FINALI")

                }

                intent.putExtra("ingredients", ingredientiPossedutiFinali)

                /*val ingredienti_posseduti = model.ingredientiPossedutiLiveData as ArrayList<String>

                intent.putExtra("ingredients", ingredienti_posseduti as Serializable)*/

                startActivity(intent)
            }



        }

    private val addToCartIngredientClickListener: AddToCartIngredientClickListener = object :
        AddToCartIngredientClickListener {

        override fun onClickAddToCartIngredient(id: Int, name: String?, image: String?) {

            model.setIngredientiToCartOnDB(
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


        private fun vibrate(){
            val vibrator = requireContext().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            val vibrationEffect1 = VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE)
            vibrator.cancel()
            vibrator.vibrate(vibrationEffect1)
        }


    }
