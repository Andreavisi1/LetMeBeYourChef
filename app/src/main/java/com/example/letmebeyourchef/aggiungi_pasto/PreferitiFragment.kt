package com.example.letmebeyourchef.aggiungi_pasto

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.letmebeyourchef.R
import com.example.letmebeyourchef.databinding.FragmentPreferitiBinding
import com.example.letmebeyourchef.model.Pasto
import kotlinx.android.synthetic.main.add_delete_layout.*


class PreferitiFragment : Fragment() {

    lateinit private var binding: FragmentPreferitiBinding


    private lateinit var recyclerViewPreferiti: RecyclerView

    private val model = AggiungiViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_preferiti, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.getPreferiti(requireArguments().getString("bottone")!!)
        recyclerViewPreferiti = binding.gridPreferiti
        recyclerViewPreferiti.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewPreferiti.setHasFixedSize(true)


        val preferitiObserver = Observer<List<Pasto>>{
            val adapter = MyAdapterPrefPers(model.preferitiLiveData.value!! as ArrayList<Pasto>)
            recyclerViewPreferiti.adapter = adapter
            adapter.setOnItemClickListener(object : MyAdapterPrefPers.onItemClickListener {
                override fun onItemClick(position: Int) {
                    openUpdateDeleteDialog(position)
                }
            })
        }
        model.preferitiLiveData.observe(viewLifecycleOwner,preferitiObserver)


    }


    @SuppressLint("ResourceAsColor")
    private fun openUpdateDeleteDialog(position: Int){
        var dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.add_delete_layout)
        dialog.window?.setBackgroundDrawable(ColorDrawable(R.color.transparent))
        Glide.with(requireContext())
            .load(model.preferitiLiveData.value!![position].image)
            .placeholder(R.drawable.no_image)
            .into(dialog.ProductImage)
        dialog.Product.text = model.preferitiLiveData.value!![position].nome
        dialog.tvMessaggio.text = "Scegli cosa vuoi fare! "


        var flag = false
        dialog.btnAddDiario.setOnClickListener {
            dialog.layout_quantita.visibility = View.VISIBLE
            val quantita = dialog.editTextQuantita.text.toString().toDouble()
            if(quantita != 0.0 && quantita.toString() != "") {
                model.setPastoOnDB(requireArguments().getString("bottone")!!,model.preferitiLiveData.value!![position].id,
                    model.preferitiLiveData.value!![position].image,model.preferitiLiveData.value!![position].nome,
                    model.preferitiLiveData.value!![position].calorie,model.preferitiLiveData.value!![position].proteine,
                    model.preferitiLiveData.value!![position].carboidrati,model.preferitiLiveData.value!![position].grassi,
                    quantita,requireContext())
                dialog.dismiss()
            }
            else {
                if (flag)
                    Toast.makeText(requireContext(),"Per favore inserisci una quantità diversa da $quantita se desideri aggiungere il prodotto al Diario",Toast.LENGTH_LONG).show()
                flag = true
            }
        }

        dialog.btnElimina.setOnClickListener {
            model.deletePreferiti(model.preferitiLiveData.value!![position].id,
                requireArguments().getString("bottone")!!,
                requireContext() )
            dialog.dismiss()
        }

        dialog.btnAnnulla.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }


}