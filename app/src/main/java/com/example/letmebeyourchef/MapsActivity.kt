package com.example.letmebeyourchef

import android.content.pm.PackageManager
import android.os.AsyncTask
import android.Manifest;
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MapsActivity : AppCompatActivity() {
    var spType: Spinner? = null
    var btfind: Button? = null
    var supportMapFragment: SupportMapFragment? = null
    var map: GoogleMap? = null
    var fusedLocationProviderClient: FusedLocationProviderClient? = null
    var currentLat = 0.0
    var currentLong = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        //Assign variable
        spType = findViewById(R.id.sp_type)
        btfind = findViewById(R.id.bt_find)
        supportMapFragment = supportFragmentManager
            .findFragmentById(R.id.google_map) as SupportMapFragment?

        //Initialize array of place type
        val placeTypeList = arrayOf("atm", "bank", "hospital", "movie_theater", "restaurant")
        //Initialize array of place name
        val placeNameList = arrayOf("ATM", "Bank", "Hospital", "Movie Theater", "Restaurant")

        //Set adapter on spinner
        spType?.setAdapter(
            ArrayAdapter(
                this@MapsActivity,
                android.R.layout.simple_spinner_dropdown_item,
                placeNameList
            )
        )

        //Initialize fused location provider client
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        //Check permission
        if (ActivityCompat.checkSelfPermission(
                this@MapsActivity, Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            //When permission granted
            //Call method
            currentLocation
        } else {
            //When permission denied
            //Request permission
            ActivityCompat.requestPermissions(
                this@MapsActivity, arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION), 44
            )
        }
        btfind?.setOnClickListener(View.OnClickListener { //Get selected position of spinner
            val i = spType?.getSelectedItemPosition()
            //Initialize url
            val url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json" +  //Url
                    "?location=" + currentLat + "," + currentLong +  //Location latitude and Logitude
                    "&radius=5000" +  //Nearby radius
                    "&types=" + placeTypeList[i!!] +  //Place type
                    "&sensor=true" +  //Sensor
                    "&key=" + resources.getString(R.string.google_map_key) //Google map key

            //Execute place task method to download json data
            PlaceTask().execute(url)
        })
    }

    private val currentLocation: Unit
        private get() {
            //Initialize task Location
            if (ActivityCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            val task = fusedLocationProviderClient!!.lastLocation
            task.addOnSuccessListener { location ->
                //when success
                if (location != null) {
                    //When location is not equal to null
                    currentLat = location.latitude
                    //Get current longitude
                    currentLong = location.longitude
                    //Sync map
                    supportMapFragment!!.getMapAsync { googleMap -> //When map is ready
                        map = googleMap
                        googleMap.addMarker(MarkerOptions().position(LatLng(currentLat, currentLong)).title ("My Location"))
                        //Zoom current location on map
                        map!!.animateCamera(
                            CameraUpdateFactory.newLatLngZoom(
                                LatLng(currentLat, currentLong), 14f
                            )
                        )
                    }
                }
            }
        }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 44) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //When permission granted
                //Call method
                currentLocation
            }
        }
    }

    private inner class PlaceTask : AsyncTask<String?, Int?, String?>() {
        @Deprecated("Deprecated in Java")
        protected override fun doInBackground(vararg strings: String?): String? {
            var data: String? = null
            data = try {
                strings[0]?.let { downloadUrl(it) }
            } catch (e: IOException) {
                throw RuntimeException(e)
            }
            return data
        }

        override fun onPostExecute(s: String?) {
            //Execute parser task
            ParserTask().execute()
        }

    }

    @Throws(IOException::class)
    private fun downloadUrl(string: String): String {
        //Initialize url
        val url = URL(string)
        //Initialize connection
        val connection = url.openConnection() as HttpURLConnection
        //Connect connection
        connection.connect()
        //Initialize input stream
        val stream = connection.inputStream
        //Initialize buffer reader
        val reader = BufferedReader(InputStreamReader(stream))
        //Initialize string builder
        val builder = StringBuilder()
        //Initialize string variable
        var line: String? = ""
        //Use while Loop
        while (reader.readLine().also { line = it } != null) {
            //Append Line
            builder.append(line)
        }
        //Get append data
        val data = builder.toString()
        //Close reader
        reader.close()
        //Return data
        return data
    }

    private inner class ParserTask : AsyncTask<String?, Int?, List<HashMap<String, String>>?>() {
        @Deprecated("Deprecated in Java")
        protected override fun doInBackground(vararg strings: String?): List<HashMap<String, String>>? {
            //Create json parser class
            val jsonParser = JsonParser()
            //Initialize hash map list
            var maplist: List<HashMap<String, String>>? = null
            var `object`: JSONObject? = null
            try {
                //Initialize json object
                `object` = strings[0]?.let { JSONObject(it) }
                //Parse json object
                maplist = `object`?.let { jsonParser.parseResult(it) }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            //Return map list
            return maplist
        }

        override fun onPostExecute(hashMaps: List<HashMap<String, String>>?) {
            //Clear map
            map!!.clear()
            //Use for loop
            for (i in hashMaps!!.indices) {
                //Initialize hash map
                val hashMapList = hashMaps[i]
                //Get latitude
                val lat = hashMapList["lat"]!!.toDouble()
                //Get longitude
                val Ing = hashMapList["Ing"]!!.toDouble()
                //Get name
                val name = hashMapList["name"]
                //Concat latitude and longitude
                val latLng = LatLng(lat, Ing)
                //Initialize marker options
                val options = MarkerOptions()
                //Set position
                options.position(latLng)
                //Set title
                options.title(name)
                //Add marker on map
                map!!.addMarker(options)
            }
        }
    }
}