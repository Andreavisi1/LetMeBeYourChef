package com.example.letmebeyourchef.ricette_preferite

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.letmebeyourchef.ricetta.ActivityDettagliRicetta
import com.example.letmebeyourchef.R
import com.example.letmebeyourchef.RequestManager
import com.example.letmebeyourchef.adapters.RicettePreferiteAdapter
import com.example.letmebeyourchef.databinding.FragmentRicettePreferiteBinding
import com.example.letmebeyourchef.listeners.IngredientFavouriteClickListener
import com.example.letmebeyourchef.listeners.RicettaClickListener
import com.example.letmebeyourchef.listeners.RicettaDeleteClickListener
import com.example.letmebeyourchef.recipeModels.FavouriteRecipe
import kotlinx.android.synthetic.main.win_layout_dialog.btn_OK
import kotlinx.android.synthetic.main.win_layout_dialog.imageViewClose
import kotlinx.android.synthetic.main.win_layout_dialog.imageViewWin


class RicettePreferiteFragment : Fragment() {

    lateinit var manager: RequestManager
    var tags: MutableList<String> = ArrayList()

    private lateinit var ricettePreferiteAdapter: RicettePreferiteAdapter

    private var  mContext: Context? = null
    var show_btn: Button? = null

    private val model = RicettePreferiteViewModel()
    private lateinit var binding: FragmentRicettePreferiteBinding


    private lateinit var recyclerViewPreferiti: RecyclerView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_ricette_preferite, container, false)
        //aggiornamento automatico view
        binding.viewModel = model
        binding.lifecycleOwner = this

        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        model.getFavoriteRecipes()
        recyclerViewPreferiti = binding.recyclerRicettePreferite
        recyclerViewPreferiti.layoutManager = GridLayoutManager(mContext, 1)
        recyclerViewPreferiti.setHasFixedSize(true)



        /*ricettePreferiteAdapter = RicettePreferiteAdapter(
            requireContext(),
            model.getRicettePreferite(),
            ricettaClickListener,
        )*/



        val preferitiObserver = Observer<List<FavouriteRecipe>>{
            val adapter = RicettePreferiteAdapter(requireContext(),
                model.ricettePreferiteLiveData.value!! as ArrayList<FavouriteRecipe>,
                ricettaClickListener, ricettaDeleteClickListener)
            recyclerViewPreferiti.adapter = adapter

            if (adapter.itemCount != 0) binding.preferitiTitle.text = "Your favourite recipes here"

            }





        model.ricettePreferiteLiveData.observe(viewLifecycleOwner,preferitiObserver)

        Log.e(binding.recyclerRicettePreferite.toString(), "EX")


        //recyclerViewPreferiti.setAdapter(ricettePreferiteAdapter)





        /*binding.searchviewHome.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            public override fun onQueryTextSubmit(query: String): Boolean {
                tags.clear()
                tags.add(query)
                manager!!.getRicetteRandom(responseListenerRicetteRandom, tags)

                return true
            }

            public override fun onQueryTextChange(s: String): Boolean {
                return false
            }
        })*/

    }


    private val ricettaClickListener: RicettaClickListener = object : RicettaClickListener {

        override fun onClickRicetta(
            id: String,
            title: String?,
            sourceName: String?,
            readyInMinutes: Int,
            servings: Int,
            sourceUrl: String?,
            image: String,
            imageType: String?,
            instructions: String?,
            spoonacularSourceUrl: String?
        ) {
            val intent = Intent(requireActivity(), ActivityDettagliRicetta::class.java)
            val extras = Bundle()
            extras.putString("id", id)
            extras.putString("image", image)
            extras.putString("sourceName", sourceName)
            extras.putString("title", title)
            extras.putInt("readyInMinutes", readyInMinutes)
            extras.putInt("servings", servings)
            extras.putString("sourceUrl", sourceUrl)
            extras.putString("imageType", imageType)
            extras.putString("instructions", instructions)
            extras.putString("spoonacularSourceUrl", spoonacularSourceUrl)
            intent.putExtras(extras)
            startActivity(intent)
        }
    }


        private val ricettaDeleteClickListener: RicettaDeleteClickListener = object : RicettaDeleteClickListener {
            override fun onClickDeleteRicetta(
                id: String,
                title: String?,
                sourceName: String?,
                readyInMinutes: Int,
                servings: Int,
                sourceUrl: String?,
                image: String,
                imageType: String?,
                instructions: String?,
                spoonacularSourceUrl: String?,
            ) {
                model.removeFavoriteRecipe(
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



