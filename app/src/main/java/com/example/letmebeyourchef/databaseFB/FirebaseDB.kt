package com.example.letmebeyourchef.databaseFB

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

/*
*
* Ottengo l'instanza del database firebase firestore
*
*/


open class FirebaseDB {
   protected var db = FirebaseFirestore.getInstance()
   protected  var auth = FirebaseAuth.getInstance().currentUser?.email.toString()
}