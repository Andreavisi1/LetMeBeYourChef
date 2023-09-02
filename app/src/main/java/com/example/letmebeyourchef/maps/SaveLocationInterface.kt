package com.example.letmebeyourchef.maps

import com.example.letmebeyourchef.model.SavedPlaceModel

interface SaveLocationInterface {
    fun onLocationClick(savedPlaceModel: SavedPlaceModel)
}