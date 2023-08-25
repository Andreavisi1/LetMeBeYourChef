package com.example.letmebeyourchef.maps


import com.example.letmebeyourchef.model.GooglePlaceModel

interface NearLocationInterface {

    fun onSaveClick(googlePlaceModel: GooglePlaceModel)

    fun onDirectionClick(googlePlaceModel: GooglePlaceModel)
}