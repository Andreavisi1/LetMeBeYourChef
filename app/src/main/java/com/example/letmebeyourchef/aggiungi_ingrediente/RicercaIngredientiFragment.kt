package com.example.letmebeyourchef.aggiungi_ingrediente

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.letmebeyourchef.R
import com.example.letmebeyourchef.RequestManager
import com.example.letmebeyourchef.adapters.IngredientiAdapter
import com.example.letmebeyourchef.databinding.FragmentRicercaIngredienteBinding
import com.example.letmebeyourchef.listeners.IngredientClickListener
import com.example.letmebeyourchef.listeners.IngredientDeleteClickListener
import com.example.letmebeyourchef.listeners.IngredientFavouriteClickListener
import com.example.letmebeyourchef.listeners.SearchIngredientsListener
import com.example.letmebeyourchef.recipeModels.ResponseFromApiSearchIngredients
import com.example.letmebeyourchef.scanner.ScannerActivity

class RicercaIngredientiFragment : Fragment() {

    var dialog: ProgressDialog? = null
    lateinit var manager: RequestManager
    private lateinit var ingredientiAdapter: IngredientiAdapter
    lateinit var recyclerView: RecyclerView
    var tags: MutableList<String> = ArrayList()

    private var  mContext: Context? = null

    private val model = RicercaIngredientiViewModel()
    private lateinit var binding: FragmentRicercaIngredienteBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_ricerca_ingrediente, container, false)
        //aggiornamento automatico view
        binding.viewModel = model
        binding.lifecycleOwner = this

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchBar1.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                tags.clear()
                tags.add(query)
                manager!!.searchIngredient(searchIngredientsListener, query)

                dialog = ProgressDialog(requireContext())
                dialog!!.setTitle("Let's see if you went shopping...")
                dialog!!.show()

                return true
            }

            override fun onQueryTextChange(s: String): Boolean {
                return false
            }
        })

        binding.btnScanner.setOnClickListener {
            val intent = Intent(requireContext(), ScannerActivity::class.java)
            /*Log.d("bottone", requireArguments().getString("bottone")!!)*/
            intent.putExtra("bottone", requireArguments().getString("bottone"))
            startActivity(intent)
            requireActivity().finish()
        }

        manager = RequestManager(requireContext())


    }

    private val searchIngredientsListener: SearchIngredientsListener =
        object : SearchIngredientsListener {
            override fun didFetch(
                response: ResponseFromApiSearchIngredients,
                message: String?
            ) {

                dialog!!.dismiss()

                recyclerView = binding.gridIngredienti
                recyclerView.layoutManager = LinearLayoutManager(requireContext())
                recyclerView.setHasFixedSize(true)
                ingredientiAdapter = IngredientiAdapter(
                    requireContext(),
                    response.results!!,
                    ingredientClickListener,
                    ingredientFavouriteClickListener,
                    ingredientDeleteClickListener
                )

                recyclerView.adapter = ingredientiAdapter

            }

            override fun didError(message: String?) {
                Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
            }
        }

    private val ingredientClickListener: IngredientClickListener = object : IngredientClickListener {

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



    private fun vibrate(){
        val vibrator = requireContext().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        val vibrationEffect1 = VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE)
        vibrator.cancel()
        vibrator.vibrate(vibrationEffect1)
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
