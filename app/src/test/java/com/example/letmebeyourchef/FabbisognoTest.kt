package com.example.letmebeyourchef

import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import com.example.letmebeyourchef.autenticazione.AuthViewModel
import com.example.letmebeyourchef.autenticazione.LoginActivity
import com.example.letmebeyourchef.autenticazione.RegisterActivity
import com.example.letmebeyourchef.databaseFB.UtenteDB
import com.example.letmebeyourchef.model.Utente
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.tasks.await
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class FabbisognoTest {

    @Before
    fun setUp(){

    }

    // Test per vedere se il metodo calculateFabbisogno calcola correttamente le calorie giornaliere

    val db = FirebaseFirestore.getInstance()

    @Test
    suspend fun check_CorrectRegistration(){

        val model = AuthViewModel()
        model.addAuthUtenteOnDB("Matthew","Bellamy","m.bellamy@muse.com","Uomo","1978-06-09", null, InstrumentationRegistry.getInstrumentation().targetContext)
        val utente = UtenteDB()
        val nome = utente.getUtente("m.bellamy@muse.com").nome
        Assert.assertEquals("Matthew", nome)
    }

   /* @Test
    fun check_UncorrectFabbisogno1(){
        val fabbisogno = Utente()
        val utente = Utente("Alessandro","Rongoni","alerong@gmail.com",1.725,true,"Uomo","2000-05-18",183,76.0,"Calcio","Climatica")
        Assert.assertNotEquals(3229, fabbisogno.calculateFabbisogno(utente.data_nascita,utente.sesso,utente.peso_attuale,utente.altezza,utente.LAF))
    }

    @Test
    fun check_UncorrectFabbisogno2(){
        val fabbisogno = Utente()
        val utente = Utente("Alessandro","Rongoni","alerong@gmail.com",1.725,true,"Uomo","2000-05-18",183,76.0,"Calcio","Climatica")
        Assert.assertNotEquals(3231, fabbisogno.calculateFabbisogno(utente.data_nascita,utente.sesso,utente.peso_attuale,utente.altezza,utente.LAF))
    }

    @Test
    fun check_LAFSedentarioFabbisogno(){
        val fabbisogno = Utente()
        val utente = Utente("Alessandro","Rongoni","alerong@gmail.com",1.2,true,"Uomo","2000-05-18",183,76.0,"Calcio","Climatica")
        Assert.assertEquals(2247, fabbisogno.calculateFabbisogno(utente.data_nascita,utente.sesso,utente.peso_attuale,utente.altezza,utente.LAF))
    }

    @Test
    fun check_DonnaFabbisogno(){
        val fabbisogno = Utente()
        val utente = Utente("Martina","Rossi","martina.rossiOF.com",1.55,false,"Donna","2002-06-23",167,55.0,"","Climatica")
        Assert.assertEquals(1239, fabbisogno.calculateFabbisogno(utente.data_nascita,utente.sesso,utente.peso_attuale,utente.altezza,utente.LAF))
    }

    @Test
    fun check_DonnaCambioEtaFabbisogno(){
        val fabbisogno = Utente()
        val utente = Utente("Martina","Rossi","martina.rossiOF.com",1.55,false,"Donna","1999-06-23",167,55.0,"","Climatica")
        Assert.assertNotEquals(1239, fabbisogno.calculateFabbisogno(utente.data_nascita,utente.sesso,utente.peso_attuale,utente.altezza,utente.LAF))
    }*/


}
