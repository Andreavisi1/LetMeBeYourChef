package com.example.letmebeyourchef.maps

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letmebeyourchef.model.GooglePlaceModel
import com.google.firebase.auth.AuthCredential
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class GoogleMapsViewModel : ViewModel() {

    private val repo = AppRepo()

    fun getNearByPlace(url: String) = repo.getPlaces(url)

    fun removePlace(userSavedLocationId: ArrayList<String>) = repo.removePlace(userSavedLocationId)

    fun addUserPlace(googlePlaceModel: GooglePlaceModel, userSavedLocationId: ArrayList<String>) =
        repo.addUserPlace(googlePlaceModel, userSavedLocationId)

    suspend fun getUserLocationId(): ArrayList<String> {

        return withContext(viewModelScope.coroutineContext) {
            val data = async { repo.getUserLocationId() }
            data
        }.await()
    }

    fun getDirection(url: String) = repo.getDirection(url)

    fun getUserLocations() = repo.getUserLocations()
}
