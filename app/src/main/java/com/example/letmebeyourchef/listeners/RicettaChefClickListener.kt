package com.example.letmebeyourchef.listeners

open interface RicettaChefClickListener {
    fun onClickRicetta(id: String, title: String?,
                       image: String, imageType: String?
    )

}