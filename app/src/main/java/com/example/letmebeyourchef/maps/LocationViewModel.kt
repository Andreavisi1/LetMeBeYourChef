package com.example.letmebeyourchef.maps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letmebeyourchef.model.GooglePlaceModel
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class LocationViewModel : ViewModel() {

    private val repo = AppRepo()

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
