package com.example.letmebeyourchef.cart

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.letmebeyourchef.GoogleMapsActivity
import com.example.letmebeyourchef.MapActivity
import com.example.letmebeyourchef.MapsActivity
import com.example.letmebeyourchef.R
import com.example.letmebeyourchef.RequestManager
import com.example.letmebeyourchef.adapters.CartAdapter
import com.example.letmebeyourchef.adapters.RicettePreferiteAdapter
import com.example.letmebeyourchef.databinding.FragmentCartBinding
import com.example.letmebeyourchef.listeners.IngredientDeleteClickListener
import com.example.letmebeyourchef.recipeModels.Ingredient

class CartFragment : Fragment() {

    var dialog: ProgressDialog? = null
    lateinit var manager: RequestManager
    var tags: MutableList<String> = ArrayList()

    private lateinit var ricettePreferiteAdapter: RicettePreferiteAdapter

    private var  mContext: Context? = null
    var show_btn: Button? = null

    private val model = CartViewModel()
    private lateinit var binding: FragmentCartBinding

    private lateinit var recyclerViewCart: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false)
        //aggiornamento automatico view
        binding.viewModel = model
        binding.lifecycleOwner = this

        dialog = ProgressDialog(requireContext())
        dialog!!.setTitle("Going shopping...")
        dialog!!.show()

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.getIngredientiCart()
        recyclerViewCart = binding.recyclerCart
        recyclerViewCart.layoutManager = GridLayoutManager(mContext, 1)
        recyclerViewCart.setHasFixedSize(true)

        val cartObserver = Observer<List<Ingredient>>{
            val adapter = CartAdapter(requireContext(),
                model.ingredientiCartLiveData.value!! as ArrayList<Ingredient>,
                ingredientDeleteClickListener)
            recyclerViewCart.adapter = adapter
1
            //if (adapter.itemCount != 0) binding.cartTitle.text = "Your shopping list"

            dialog!!.dismiss()

        }

        model.ingredientiCartLiveData.observe(viewLifecycleOwner,cartObserver)



        binding.searchBtn.setOnClickListener {

            val intent = Intent(requireActivity(), GoogleMapsActivity::class.java)

            startActivity(intent)
        }


    }




        private val ingredientDeleteClickListener: IngredientDeleteClickListener = object : IngredientDeleteClickListener {
            override fun onClickDeleteIngredient(id: Int, name: String?, image: String?) {
                model.removeIngredientFromCart(
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



