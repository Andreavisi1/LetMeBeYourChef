package com.example.letmebeyourchef.maps

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.letmebeyourchef.R
import com.example.letmebeyourchef.adapters.SavedPlaceAdapter
import com.example.letmebeyourchef.databinding.FragmentSavedPlaceBinding
import com.example.letmebeyourchef.listeners.PlaceFavouriteClickListener
import com.example.letmebeyourchef.model.SavedPlaceModel
import com.example.letmebeyourchef.utils.LoadingDialog
import com.google.android.material.snackbar.Snackbar

class SavedPlaceFragment : Fragment(), SaveLocationInterface {

    private lateinit var binding: FragmentSavedPlaceBinding
    private lateinit var savedPlaceModelList: ArrayList<SavedPlaceModel>
    private lateinit var loadingDialog: LoadingDialog
    private val locationViewModel: LocationViewModel by viewModels()
    private lateinit var placeAdapter: SavedPlaceAdapter
    private val model = GoogleMapsViewModel()
    private lateinit var recyclerViewPlacesPreferiti: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSavedPlaceBinding.inflate(inflater, container, false)

        savedPlaceModelList = ArrayList()
        loadingDialog = LoadingDialog(requireActivity())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Saved Places"

        Glide.with(requireContext())
            .load(R.raw.programmer)
            .into(binding.imageViewProgrammer)

        model.getPlacesPreferiti()
        recyclerViewPlacesPreferiti = binding.savedRecyclerView
        recyclerViewPlacesPreferiti.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewPlacesPreferiti.setHasFixedSize(true)

        val placesPreferitiObserver = Observer<List<SavedPlaceModel>>{
            val adapter = SavedPlaceAdapter(requireContext(),
                model.placesPreferitiLiveData.value!! as ArrayList<SavedPlaceModel>,
                placeFavouriteClickListener)
            recyclerViewPlacesPreferiti.adapter = adapter


            loadingDialog!!.stopLoading()

        }

        model.placesPreferitiLiveData.observe(viewLifecycleOwner,placesPreferitiObserver)

    }

    private val placeFavouriteClickListener: PlaceFavouriteClickListener = object :
        PlaceFavouriteClickListener {
        override fun onClickFavouritePlace(
            name:String,
            address:String,
            placeId:String,
            totalRating:Int,
            rating:Double,
            lat:Double,
            lng:Double
        ) {
            model.setPlacesPreferitiOnDB(
                name,
                address,
                placeId,
                totalRating,
                rating,
                lat,
                lng,
                requireContext()
            )
        }
    }

    override fun onLocationClick(savedPlaceModel: SavedPlaceModel) {
        val intent = Intent(requireContext(), DirectionActivity::class.java)
        intent.putExtra("placeId", savedPlaceModel.placeId)
        intent.putExtra("lat", savedPlaceModel.lat)
        intent.putExtra("lng", savedPlaceModel.lng)

        startActivity(intent)
    }

    private fun getSavedPlaces() {
        lifecycleScope.launchWhenStarted {
            locationViewModel.getUserLocations().collect {
                when (it) {
                    is State.Loading -> {
                        if (it.flag == true) {
                            Log.d("TAG", "getSavedPlaces: called")
                            loadingDialog.startLoading()
                        }
                    }

                    is State.Success -> {
                        loadingDialog.stopLoading()
                        savedPlaceModelList = it.data as ArrayList<SavedPlaceModel>
                        Toast.makeText(
                            requireContext(),
                            "${savedPlaceModelList.size}",
                            Toast.LENGTH_SHORT
                        ).show()

                        placeAdapter.submitList(savedPlaceModelList)

                    }
                    is State.Failed -> {
                        loadingDialog.stopLoading()
                        Snackbar.make(
                            binding.root, it.error,
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }


}