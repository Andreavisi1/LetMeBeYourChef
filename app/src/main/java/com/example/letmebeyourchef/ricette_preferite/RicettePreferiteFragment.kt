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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

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
        recyclerViewPreferiti.setLayoutManager(GridLayoutManager(mContext, 1))
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

            if (adapter.getItemCount() != 0) binding.preferitiTitle.text = "Your favourite recipes here"

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

        public override fun onClickRicetta(
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
            public override fun onClickDeleteRicetta(
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

    override fun onDestroy() {
        super.onDestroy()
        //setDiario()


    }

    override fun onPause() {
        super.onPause()
        //setDiario()

    }


    private fun startAnimation(glass : ImageView){
        glass.setBackgroundResource(R.drawable.filling_animation)
        val frameAnimation: AnimationDrawable = glass.background as AnimationDrawable
        frameAnimation.start()
    }



    @SuppressLint("ResourceAsColor")
    private fun openCongratulazioni() {
        var dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.win_layout_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(R.color.transparent))
        dialog.imageViewClose.setOnClickListener {
            dialog.dismiss()
        }
        dialog.btn_OK.setOnClickListener {
            dialog.dismiss()
        }
        Glide.with(requireContext())
            .load(R.raw.awards)
            .into(dialog.imageViewWin)
        dialog.show()
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






/*lateinit var manager: RequestManager
private lateinit var ricetteRandomAdapter: RicetteRandomAdapter
lateinit var recyclerView: RecyclerView
var tags: MutableList<String> = ArrayList()

private var  mContext: Context? = null

private var model = RicettePreferiteViewModel()
private lateinit var binding: FragmentRicettePreferiteBinding


override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    }


override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_ricette_preferite, container, false)
    //aggiornamento automatico view
    binding.viewModel = model
    binding.lifecycleOwner = this
    return binding.root
}

override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    model = ViewModelProvider(requireActivity()).get(RicettePreferiteViewModel::class.java)
    binding.viewModel = model

    /*
    // Ottieni la lista delle ricette preferite dal ViewModel
    model.getFavoriteRecipes().observe(viewLifecycleOwner, { favoriteRecipes ->
        // Aggiorna la visualizzazione delle ricette preferite
        updateFavoriteRecipes(favoriteRecipes)




    setupRecyclerView()
    observeFavoriteRecipes() */

    }




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
    })

    val arrayAdapter: ArrayAdapter<*> = ArrayAdapter.createFromResource(
        requireContext(),
        R.array.tags,
        R.layout.spinner_text
    )
    arrayAdapter.setDropDownViewResource(R.layout.spinner_inner_text)
    binding.spinnerTags.setAdapter(arrayAdapter)
    binding.spinnerTags.setOnItemSelectedListener(spinnerSelectedListener)
    manager = RequestManager(requireContext())
    //        manager.getRicetteRandom(responseListenerRicetteRandom);
//        dialog.show();*/



private val responseListenerRicetteRandom: ResponseListenerRicetteRandom =
    object : ResponseListenerRicetteRandom {
        public override fun didFetch(
            response: ResponseFromApiRicetteRandom?,
            message: String?
        ) {

            recyclerView = binding.recyclerPreferiti
            recyclerView.setHasFixedSize(true)
            recyclerView.setLayoutManager(GridLayoutManager(mContext, 1))
            ricetteRandomAdapter = RicetteRandomAdapter(
                requireContext(),
                response!!.recipes,
                ricettaClickListener,
            )

            recyclerView.setAdapter(ricetteRandomAdapter)

        }

        public override fun didError(message: String?) {
            Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
        }
    }
private val spinnerSelectedListener: AdapterView.OnItemSelectedListener =
    object : AdapterView.OnItemSelectedListener {
        public override fun onItemSelected(
            adapterView: AdapterView<*>,
            view: View?,
            i: Int,
            l: Long
        ) {
            tags.clear()
            tags.add(adapterView.getSelectedItem().toString())
            manager!!.getRicetteRandom(responseListenerRicetteRandom, tags)
        }

        public override fun onNothingSelected(adapterView: AdapterView<*>?) {}
    }
private val ricettaClickListener: RicettaClickListener = object : RicettaClickListener {
    public override fun onClickRicetta(id: String?) {
        startActivity(
            Intent(requireActivity(), ActivityDettagliRicetta::class.java)
                .putExtra("id", id)
        )
    }
}

override fun onDestroy() {
    super.onDestroy()
    //setDiario()



}

override fun onPause() {
    super.onPause()
    //setDiario()

}


private fun startAnimation(glass : ImageView){
    glass.setBackgroundResource(R.drawable.filling_animation)
    val frameAnimation: AnimationDrawable = glass.background as AnimationDrawable
    frameAnimation.start()
}



@SuppressLint("ResourceAsColor")
private fun openCongratulazioni() {
    var dialog = Dialog(requireContext())
    dialog.setContentView(R.layout.win_layout_dialog)
    dialog.window?.setBackgroundDrawable(ColorDrawable(R.color.transparent))
    dialog.imageViewClose.setOnClickListener {
        dialog.dismiss()
    }
    dialog.btn_OK.setOnClickListener {
        dialog.dismiss()
    }
    Glide.with(requireContext())
        .load(R.raw.awards)
        .into(dialog.imageViewWin)
    dialog.show()
}

private fun vibrate(){
    val vibrator = requireContext().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    val vibrationEffect1 = VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE)
    vibrator.cancel()
    vibrator.vibrate(vibrationEffect1)
}

private fun updateFavoriteRecipes(favoriteRecipes: List<ResponseFromApiDettagliRicetta>) {
    // Aggiorna la visualizzazione delle ricette preferite nel tuo layout
    // Ad esempio, puoi utilizzare un adapter per visualizzare le ricette in una RecyclerView
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

/*private fun setupRecyclerView() {
    ricettePreferiteAdapter = RicettePreferiteAdapter { recipe ->
        // Gestisci il clic su un elemento di ricetta preferita
        // Puoi eseguire azioni come aprire i dettagli della ricetta, ecc.
    }

    binding.recyclerViewFavoriteRecipes.apply {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = favoriteRecipesAdapter
        addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
    }
}

private fun observeFavoriteRecipes() {
model.getFavoriteRecipes().observe(viewLifecycleOwner, { favoriteRecipes ->
    favoriteRecipesAdapter.submitList(favoriteRecipes)
    binding.textNoFavoriteRecipes.visibility = if (favoriteRecipes.isNotEmpty()) View.GONE else View.VISIBLE
})
}*/*/


