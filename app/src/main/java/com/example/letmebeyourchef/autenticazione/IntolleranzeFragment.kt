package com.example.letmebeyourchef.autenticazione

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.example.letmebeyourchef.R
import com.example.letmebeyourchef.databinding.FragmentIntolleranzeBinding
import com.example.letmebeyourchef.model.Utente
import kotlinx.android.synthetic.main.fragment_intolleranze.*


class IntolleranzeFragment : Fragment() {
    lateinit var binding: FragmentIntolleranzeBinding
    private lateinit var utente: Utente
    private val args: IntolleranzeFragmentArgs by navArgs()
    private lateinit var cbIntolleranze : ArrayList<RadioButton>
    var checked = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_intolleranze, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.sBIntolleranze.setOnCheckedChangeListener { _, isChecked ->
            binding.
            RadioGroupIntolleranze.isClickable = isChecked
            checked = isChecked
        }

        cbIntolleranze = arrayListOf(binding.cBDairy, binding.cBGluten ,binding.cBPeanut,
            binding.cBGrain, binding.cBSeafood,
            binding.cBEgg, binding.cBSesame, binding.cBShellfish, binding.cBSoy,
            binding.cBSulfite, binding.cBTreeNut, binding.cBWheat, binding.cBAltro)

        utente = args.utente
        utente.intolleranze = ""
        takeChecked()
        binding.btAvantiIntolleranze.setOnClickListener {

                val intent = Intent(context, RegisterActivity::class.java)
                intent.putExtra("utente", utente)
                startActivity(intent)

        }
    }

    private fun takeChecked() {
        var listener = View.OnClickListener { v ->
            when(v.id){
                R.id.cB_dairy -> utente.intolleranze = "Dairy"
                R.id.cB_gluten -> utente.intolleranze = cB_gluten.text.toString()
                R.id.cB_peanut -> utente.intolleranze = cB_peanut.text.toString()
                R.id.cB_grain -> utente.intolleranze = cB_grain.text.toString()
                R.id.cB_seafood -> utente.intolleranze = cB_seafood.text.toString()
                R.id.cB_egg -> utente.intolleranze = cB_egg.text.toString()
                R.id.cB_sesame-> utente.intolleranze = cB_sesame.text.toString()
                R.id.cB_shellfish -> utente.intolleranze = cB_shellfish.text.toString()
                R.id.cB_soy -> utente.intolleranze = cB_soy.text.toString()
                R.id.cB_sulfite -> utente.intolleranze = cB_sulfite.text.toString()
                R.id.cB_tree_nut -> utente.intolleranze = cB_tree_nut.text.toString()
                R.id.cB_wheat -> utente.intolleranze = cB_wheat.text.toString()
                R.id.cB_altro -> utente.intolleranze = cB_altro.text.toString()

            }
        }

        for(radioButton in cbIntolleranze){
            radioButton.setOnClickListener(listener)
        }

    }

    override fun onStop() {
        super.onStop()
        binding.RadioGroupIntolleranze.clearCheck()
    }


}