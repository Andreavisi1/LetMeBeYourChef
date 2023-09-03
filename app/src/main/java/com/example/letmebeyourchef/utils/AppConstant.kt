package com.example.letmebeyourchef.utils

import com.example.letmebeyourchef.R
import com.example.letmebeyourchef.model.PlaceModel


class AppConstant {
    companion object {
        @JvmStatic
        val STORAGE_REQUEST_CODE = 1000

        const val LOCATION_REQUEST_CODE = 2000


        @JvmStatic
        val placesName =
            listOf<PlaceModel>(
                PlaceModel(1, R.drawable.ic_shopping_cart, "Supermarket", "supermarket"),
                PlaceModel(2, R.drawable.ic_storefront, "Bakery", "bakery"),
                PlaceModel(3, R.drawable.ic_mall, "Shopping Mall", "shopping_mall"),
                PlaceModel(4, R.drawable.ic_restaurant, "Restaurant", "restaurant"),
                PlaceModel(5, R.drawable.coffee, "Cafè", "cafe")
            )
    }
}