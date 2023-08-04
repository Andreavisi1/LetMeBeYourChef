package com.example.letmebeyourchef.listeners

import android.content.Context

open interface RicettaChefClickListener {
    fun onClickRicetta(id: String, title: String?,
                       image: String, imageType: String?
    )

}