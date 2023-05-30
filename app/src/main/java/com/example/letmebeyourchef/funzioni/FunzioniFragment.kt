package com.example.letmebeyourchef.funzioni

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.letmebeyourchef.R
import com.example.letmebeyourchef.databinding.FragmentFunzioniBinding
import kotlinx.android.synthetic.main.win_layout_dialog.*

class FunzioniFragment : Fragment() {


    private lateinit var binding : FragmentFunzioniBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_funzioni,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(requireContext())
            .load(R.raw.programmer)
            .into(binding.imageViewProgrammer)
    }


}