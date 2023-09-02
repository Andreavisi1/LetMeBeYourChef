package com.example.letmebeyourchef.maps

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letmebeyourchef.databaseFB.PlacesPreferitiDB
import com.example.letmebeyourchef.model.GooglePlaceModel
import com.example.letmebeyourchef.model.SavedPlaceModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GoogleMapsViewModel : ViewModel() {

    private val repo = AppRepo()
    private val placesPreferitiDB = PlacesPreferitiDB()
    private val auth = FirebaseAuth.getInstance()

    private var _placesPreferitiLiveData = MutableLiveData<List<SavedPlaceModel>>()
    val placesPreferitiLiveData: LiveData<List<SavedPlaceModel>>
        get() = _placesPreferitiLiveData

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

    fun getPlacesPreferiti(){
        viewModelScope.launch {
            _placesPreferitiLiveData.value =
                placesPreferitiDB.getPlacesPreferiti(auth.currentUser!!.email!!)
        }
    }

    fun setPlacesPreferitiOnDB(
        name:String="",
        address:String="",
        placeId:String="",
        totalRating:Int=0,
        rating:Double=0.0,
        lat:Double=0.0,
        lng:Double=0.0,
        context: Context
    )
    {
        viewModelScope.launch {
            if (placesPreferitiDB.setPlacesPreferiti(name, address, placeId, totalRating, rating, lat, lng, auth.currentUser?.email!!
                )
            ) {
                Toast.makeText(context, "$name added to favourite places list", Toast.LENGTH_LONG).show()
            } else
                Toast.makeText(context, "Adding $name to favourites failed", Toast.LENGTH_LONG).show()
        }
    }

    fun removePlace(name: String, context: Context) {
        viewModelScope.launch {
            if(placesPreferitiDB.deletePlacePreferito(auth.currentUser!!.email!!, name)){
                Toast.makeText(context,"Place correctly deleted from favourites", Toast.LENGTH_LONG).show()
                getPlacesPreferiti()
            }else{
                Toast.makeText(context,"ATTENTION!\nPlace not deleted", Toast.LENGTH_LONG).show()
            }
        }
    }
}
