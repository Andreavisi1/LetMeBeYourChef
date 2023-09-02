package com.example.letmebeyourchef.chef

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.letmebeyourchef.R
import com.example.letmebeyourchef.RequestManager
import com.example.letmebeyourchef.adapters.ChefAdapter
import com.example.letmebeyourchef.databinding.ActivityChefBinding
import com.example.letmebeyourchef.listeners.AddToCartIngredientClickListener
import com.example.letmebeyourchef.listeners.RecipesByIngredientsListener
import com.example.letmebeyourchef.listeners.RicettaClickListener
import com.example.letmebeyourchef.recipeModels.ResponseFromApiRecipesByIngredients
import com.example.letmebeyourchef.ricetta.ActivityDettagliRicetta

class ChefActivity : AppCompatActivity() {
    var id: Int = 0
    var sourceName: String? = null
    var readyInMinutes: Int = 0
    var servings: Int = 0
    var sourceUrl: String? = null
    var image: String? = null
    var imageType: String? = null
    var instructions: String? = null
    var spoonacularSourceUrl: String? = null

    var tags: MutableList<String> = ArrayList()

    private val model = ChefViewModel()

    private lateinit var binding: ActivityChefBinding

    var manager: RequestManager? = null

    var dialog: ProgressDialog? = null

    var ricetteChefAdapter: ChefAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chef)



        val ingredienti_posseduti : String = intent.extras!!.getString("ingredients")!!

        manager = RequestManager(this)

        tags.clear()
//        tags.add(query)
        manager!!.getRecipesByIngredients(recipesByIngredientsListener, ingredienti_posseduti)

        /*tags.clear()
        tags.add(query)
        manager.getRicetteRandom(responseListenerRicetteRandom, tags)*/

        binding.chefToolbar.title =  ("Your chef proposes to you:")


        //var bottomNav = binding.bottomNavigation

        //binding.bottomNavigation.selectTabById(R.id.ricerca,true)

        /*bottomNav.setOnTabSelectListener(object : AnimatedBottomBar.OnTabSelectListener {
            override fun onTabSelected(
                lastIndex: Int,
                lastTab: AnimatedBottomBar.Tab?,
                newIndex: Int,
                newTab: AnimatedBottomBar.Tab
            ) {
                checkTabToReplace(newIndex)

            }


        })*/
        dialog = ProgressDialog(this)
        dialog!!.setTitle("Chef is cooking...")
        dialog!!.show()


    }

    private val recipesByIngredientsListener: RecipesByIngredientsListener = object : RecipesByIngredientsListener {
        override fun didFetch(
            response: List<ResponseFromApiRecipesByIngredients>,
            message: String?
        ) {

            dialog!!.dismiss()

            binding.recyclerRicetteChef!!.setHasFixedSize(true)
            binding.recyclerRicetteChef!!.layoutManager = LinearLayoutManager(
                this@ChefActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            ricetteChefAdapter =
                ChefAdapter(this@ChefActivity, response, ricettaClickListener, addToCartListener)
            binding.recyclerRicetteChef!!.adapter = ricetteChefAdapter
        }

        override fun didError(message: String?) {
            Toast.makeText(this@ChefActivity, message, Toast.LENGTH_SHORT).show()
        }
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
            val intent = Intent(this@ChefActivity, ActivityDettagliRicetta::class.java)
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

    /*private val ricettaClickListener: RicettaChefClickListener = object : RicettaChefClickListener {
        override fun onClickRicetta(
            id: String,
            title: String?,
            image: String,
            imageType: String?
        ) {

            val intent = Intent(this@ChefActivity, ActivityDettagliRicetta::class.java)
            intent.putExtra("id", id)
            startActivity(intent)

            /*startActivity(
                Intent(this@ChefActivity, ActivityDettagliRicetta::class.java)
                    .putExtra("id", id)
            )*/
        }
    }*/

    private val addToCartListener: AddToCartIngredientClickListener = object :
        AddToCartIngredientClickListener {

        override fun onClickAddToCartIngredient(
            id: Int,
            name: String?,
            image: String?
        ) {
            model.setIngredientToCartDB(
                id, name, image, this@ChefActivity)
        }
    }



    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransiction = fragmentManager.beginTransaction()
        fragmentTransiction.replace(R.id.frame_layout, fragment)
        fragmentTransiction.commit()

    }


}