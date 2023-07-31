package com.example.letmebeyourchef.ricetta

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.letmebeyourchef.R
import com.example.letmebeyourchef.RequestManager
import com.example.letmebeyourchef.adapters.IngredientiAdapter
import com.example.letmebeyourchef.adapters.IstruzioniAdapter
import com.example.letmebeyourchef.adapters.RicetteSimiliAdapter
import com.example.letmebeyourchef.listeners.DettagliRicettaListener
import com.example.letmebeyourchef.listeners.IstruzioniListener
import com.example.letmebeyourchef.listeners.NutritionLabelListener
import com.example.letmebeyourchef.listeners.RicettaClickListener
import com.example.letmebeyourchef.listeners.RicetteSimiliListener
import com.example.letmebeyourchef.recipeModels.FavouriteRecipe
import com.example.letmebeyourchef.recipeModels.Recipe
import com.example.letmebeyourchef.recipeModels.ResponseFromApiDettagliRicetta
import com.example.letmebeyourchef.recipeModels.ResponseFromApiIstruzioni
import com.example.letmebeyourchef.recipeModels.ResponseFromApiNutritionLabel
import com.example.letmebeyourchef.recipeModels.ResponseFromApiRicetteSimili
import com.example.letmebeyourchef.ricette_preferite.RicettePreferiteViewModel
import com.squareup.picasso.Picasso

class ActivityDettagliRicetta constructor() : AppCompatActivity() {
    var id: Int = 0
    var sourceName: String? = null
    var readyInMinutes: Int = 0
    var servings: Int = 0
    var sourceUrl: String? = null
    var image: String? = null
    var imageType: String? = null
    var instructions: String? = null
    var spoonacularSourceUrl: String? = null

    var textView_nome_ricetta: TextView? = null
    var textView_source_ricetta: TextView? = null
    var textView_descrizione_ricetta: TextView? = null
    var imageView_immagine_ricetta: ImageView? = null
    var recycler_ingredienti_ricetta: RecyclerView? = null
    var recycler_ricette_simili: RecyclerView? = null
    var recycler_istruzioni: RecyclerView? = null
    var like_button: Button? = null
    var nutrition_label_button: Button? = null
    var manager: RequestManager? = null
    var dialog: ProgressDialog? = null
    var ingredientiAdapter: IngredientiAdapter? = null
    var ricetteSimiliAdapter: RicetteSimiliAdapter? = null
    var istruzioniAdapter: IstruzioniAdapter? = null


    private val model = RicettePreferiteViewModel()

    var selectedRecipe: FavouriteRecipe? = null

    private var ricette_preferite = HashMap<String, String?>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dettagli_ricetta)
        findViews()

        getExtra()


        manager = RequestManager(this)
        manager!!.getDettagliRicetta(dettagliRicettaListener, id)
        manager!!.getRicetteSimili(ricetteSimiliListener, id)
        manager!!.getIstruzioni(istruzioniListener, id)
        manager!!.getNutritionLabel(nutritionLabelListener, id)

        like_button?.setOnClickListener(ricettePreferiteListener)
        nutrition_label_button?.setOnClickListener(tabellaNutrizionaleListener)

        dialog = ProgressDialog(this)
        dialog!!.setTitle("Loading details...")
        dialog!!.show()
    }

    private fun findViews() {
        textView_nome_ricetta = findViewById(R.id.textView_nome_ricetta)
        textView_source_ricetta = findViewById(R.id.textView_source_ricetta)
        textView_descrizione_ricetta = findViewById(R.id.textView_descrizione_ricetta)
        imageView_immagine_ricetta = findViewById(R.id.imageView_immagine_ricetta)
        recycler_ingredienti_ricetta = findViewById(R.id.recycler_ingredienti_ricetta)
        recycler_ricette_simili = findViewById(R.id.recycler_ricette_simili)
        recycler_istruzioni = findViewById(R.id.recycler_istruzioni)
        like_button = findViewById(R.id.likebutton)
        nutrition_label_button = findViewById(R.id.nutrition_label_button)
    }

    private val dettagliRicettaListener: DettagliRicettaListener =
        object : DettagliRicettaListener {
            public override fun didFetch(
                response: ResponseFromApiDettagliRicetta?,
                message: String?
            ) {
                dialog!!.dismiss()

                textView_nome_ricetta!!.setText(response!!.title)
                textView_source_ricetta!!.setText(response.sourceName)
                textView_descrizione_ricetta!!.setText(Html.fromHtml(response.summary))
                Picasso.get().load(response.image).into(imageView_immagine_ricetta)
                recycler_ingredienti_ricetta!!.setHasFixedSize(true)
                recycler_ingredienti_ricetta!!.setLayoutManager(
                    LinearLayoutManager(
                        this@ActivityDettagliRicetta,
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )
                )
                ingredientiAdapter =
                    IngredientiAdapter(this@ActivityDettagliRicetta, response.extendedIngredients)
                recycler_ingredienti_ricetta!!.setAdapter(ingredientiAdapter)
            }

            public override fun didError(message: String?) {
                Toast.makeText(this@ActivityDettagliRicetta, message, Toast.LENGTH_SHORT).show()
            }
        }
    private val ricetteSimiliListener: RicetteSimiliListener = object : RicetteSimiliListener {
        public override fun didFetch(
            response: List<ResponseFromApiRicetteSimili>,
            message: String?
        ) {
            recycler_ricette_simili!!.setHasFixedSize(true)
            recycler_ricette_simili!!.setLayoutManager(
                LinearLayoutManager(
                    this@ActivityDettagliRicetta,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
            )
            ricetteSimiliAdapter =
                RicetteSimiliAdapter(this@ActivityDettagliRicetta, response, ricettaClickListener)
            recycler_ricette_simili!!.setAdapter(ricetteSimiliAdapter)
        }

        public override fun didError(message: String?) {
            Toast.makeText(this@ActivityDettagliRicetta, message, Toast.LENGTH_SHORT).show()
        }
    }

    /*    private val ricettePreferiteListener: RicettePreferiteListener =
        object : RicettePreferiteListener {
            public override fun didFetch(
                response: Recipe?,
                message: String?
            ) {
                dialog!!.dismiss()



                selectedRecipe = response



                textView_nome_ricetta!!.setText(response!!.title)
                textView_source_ricetta!!.setText(response.sourceName)
                textView_descrizione_ricetta!!.setText(Html.fromHtml(response.summary))
                Picasso.get().load(response.image).into(imageView_immagine_ricetta)
                recycler_ingredienti_ricetta!!.setHasFixedSize(true)
                recycler_ingredienti_ricetta!!.setLayoutManager(
                    LinearLayoutManager(
                        this@ActivityDettagliRicetta,
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )
                )
                ingredientiAdapter =
                    IngredientiAdapter(this@ActivityDettagliRicetta, response.extendedIngredients)
                recycler_ingredienti_ricetta!!.setAdapter(ingredientiAdapter)
            }

            public override fun didError(message: String?) {
                Toast.makeText(this@ActivityDettagliRicetta, message, Toast.LENGTH_SHORT).show()
            }
        }*/

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
            startActivity(
                Intent(this@ActivityDettagliRicetta, ActivityDettagliRicetta::class.java)
                    .putExtra("id", id)
            )
        }
    }
    private val istruzioniListener: IstruzioniListener = object : IstruzioniListener {
        public override fun didFetch(response: List<ResponseFromApiIstruzioni>, message: String?) {
            recycler_istruzioni!!.setHasFixedSize(true)
            recycler_istruzioni!!.setLayoutManager(
                LinearLayoutManager(
                    this@ActivityDettagliRicetta,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            )
            istruzioniAdapter = IstruzioniAdapter(this@ActivityDettagliRicetta, response)
            recycler_istruzioni!!.setAdapter(istruzioniAdapter)
        }

        public override fun didError(message: String?) {}
    }


    /*private fun onRecipeSelected(recipe: Recipe) {
        selectedRecipe = recipe
    }
    */


    fun addToFavorites(view: View) { // Aggiungi la ricetta ai preferiti
        val viewModel = ViewModelProvider(this).get(RicettePreferiteViewModel::class.java)
        viewModel.addFavoriteRecipe(selectedRecipe)

        // Visualizza un messaggio di conferma all'utente
        Toast.makeText(this, "Ricetta aggiunta ai preferiti", Toast.LENGTH_SHORT).show()
    }

    private val nutritionLabelListener: NutritionLabelListener =
        object : NutritionLabelListener {
            public override fun didFetch(
                response: ResponseFromApiNutritionLabel?,
                message: String?
            ) {
                dialog!!.dismiss()


            }

            public override fun didError(message: String?) {
                Toast.makeText(this@ActivityDettagliRicetta, message, Toast.LENGTH_SHORT).show()
            }
        }

    fun seeNutritionLabel(view: View) { // Aggiungi la ricetta ai preferiti
        manager!!.getNutritionLabel(nutritionLabelListener, id)

    }

    private fun getExtra() {

        val extras = intent.extras

        id = extras!!.getString("id")!!.toInt()
        image = extras!!.getString("image")!!
        sourceName = extras!!.getString("sourceName")!!
        title = extras!!.getString("title")!!
        readyInMinutes = extras!!.getInt("readyInMinutes")!!.toInt()
        servings = extras!!.getInt("servings")!!.toInt()
        sourceUrl = extras!!.getString("sourceUrl")!!
        imageType = extras!!.getString("imageType")!!
        instructions = extras!!.getString("instructions")!!
        spoonacularSourceUrl = extras!!.getString("spoonacularSourceUrl")!!
    }

    private val ricettePreferiteListener = View.OnClickListener { view ->
        model.setRicettePreferiteOnDB(
            id.toString(), title.toString()!!, sourceName!!, readyInMinutes!!, servings!!,
            sourceUrl.toString()!!, image!!, imageType!!, instructions!!, spoonacularSourceUrl!!,
            this@ActivityDettagliRicetta
        )
    }

    private val tabellaNutrizionaleListener = View.OnClickListener { view ->
        var inflater = LayoutInflater.from(this)
        var popupview = inflater.inflate(R.layout.nutrition_label, null, false)
        var image = popupview.findViewById<ImageView>(R.id.imagepopup)

        image.setImageResource(R.drawable.book)

        var close = popupview.findViewById<ImageView>(R.id.close)

        var builder = PopupWindow(
            popupview, LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT, true
        )

        builder.setBackgroundDrawable(getDrawable(R.drawable.background))
        builder.animationStyle = R.style.dialogAnimation
        builder.showAtLocation(
            this.findViewById(R.id.linearLayout),
            Gravity.CENTER, 0, 0
        )

        close.setOnClickListener {
            builder.dismiss()
        }

        /*    private val ricettePreferiteListener: RicettePreferiteListener =
        object : RicettePreferiteListener {
            public override fun didFetch(
                response: Recipe?,
                message: String?
            ) {
                dialog!!.dismiss()

                    model.setRicettePreferiteOnDB(
                        ricette_preferite["id"]!!, ricette_preferite["title"]!!, ricette_preferite["sourceName"]!!,
                        ricette_preferite["readyInMinutes"]!!.toInt(), ricette_preferite["servings"]!!.toInt(), ricette_preferite["sourceUrl"]!!,
                        ricette_preferite["image"]!!, ricette_preferite["imageType"]!!, ricette_preferite["instructions"]!!,
                        ricette_preferite["spoonacularSourceUrl"]!!, this@ActivityDettagliRicetta
                    )
                    finish()


            }

            public override fun didError(message: String?) {
                Toast.makeText(this@ActivityDettagliRicetta, message, Toast.LENGTH_SHORT).show()
            }
        }*/
    }
}