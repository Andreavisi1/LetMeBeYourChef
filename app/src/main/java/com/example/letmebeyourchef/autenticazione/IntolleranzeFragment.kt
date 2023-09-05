package com.example.letmebeyourchef.autenticazione

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RadioGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.letmebeyourchef.R
import com.example.letmebeyourchef.databinding.FragmentIntolleranzeBinding
import com.example.letmebeyourchef.model.Utente


class IntolleranzeFragment : Fragment() {
    lateinit var binding: FragmentIntolleranzeBinding
    private lateinit var utente: Utente
    private val args: IntolleranzeFragmentArgs by navArgs()
    private lateinit var cbIntolleranze : ArrayList<CheckBox>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_intolleranze, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cbIntolleranze = arrayListOf(binding.cBDairy, binding.cBGluten ,binding.cBPeanut,
            binding.cBGrain, binding.cBSeafood,
            binding.cBEgg, binding.cBSesame, binding.cBShellfish, binding.cBSoy,
            binding.cBSulfite, binding.cBTreeNut, binding.cBWheat, binding.cBAltro)

        utente = args.utente
        utente.intolleranze = ArrayList()
        takeChecked()
        binding.btAvantiIntolleranze.setOnClickListener {

                val intent = Intent(context, RegisterActivity::class.java)
                intent.putExtra("utente", utente)
                startActivity(intent)

        }
    }

    private fun enableDisableRadioGroup(groupId: RadioGroup, isEnable: Boolean) {
        for (i in 0 until groupId.childCount) {
            groupId.getChildAt(i).isEnabled = isEnable
        }
    }

    private fun takeChecked() {
        var listener = View.OnClickListener { v ->
            when(v.id){
                R.id.cB_dairy -> utente.intolleranze?.add("Dairy")
                R.id.cB_gluten -> utente.intolleranze?.add("Gluten")
                R.id.cB_peanut -> utente.intolleranze?.add("Peanut")
                R.id.cB_grain -> utente.intolleranze?.add("Grain")
                R.id.cB_seafood -> utente.intolleranze?.add("Seafood")
                R.id.cB_egg -> utente.intolleranze?.add("Egg")
                R.id.cB_sesame-> utente.intolleranze?.add("Sesame")
                R.id.cB_shellfish -> utente.intolleranze?.add("Shellfish")
                R.id.cB_soy -> utente.intolleranze?.add("Soy")
                R.id.cB_sulfite -> utente.intolleranze?.add("Sulfite")
                R.id.cB_tree_nut -> utente.intolleranze?.add("Tree Nut")
                R.id.cB_wheat -> utente.intolleranze?.add("Wheat")
                R.id.cB_altro -> utente.intolleranze?.add("Other")
            }
        }

        for(checkBox in cbIntolleranze){
            checkBox.setOnClickListener(listener)
        }

    }

    override fun onStop() {
        super.onStop()
        binding.RadioGroupIntolleranze.clearCheck()
    }


}