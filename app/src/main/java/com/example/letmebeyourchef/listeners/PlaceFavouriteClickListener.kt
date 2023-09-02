package com.example.letmebeyourchef.listeners

open interface PlaceFavouriteClickListener {
    fun onClickFavouritePlace(name:String="",
                              address:String="",
                              placeId:String="",
                              totalRating:Int=0,
                              rating:Double=0.0,
                              lat:Double=0.0,
                              lng:Double=0.0)

}