package com.example.letmebeyourchef.home

import android.widget.DatePicker
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.PickerActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.letmebeyourchef.R
import com.example.letmebeyourchef.autenticazione.InizioActivity
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
internal class InizioActivityTest{
    private lateinit var scenario: ActivityScenario<InizioActivity>

    @Before
    fun setUp(){
        scenario = launchActivity()
        scenario.moveToState(Lifecycle.State.RESUMED)
    }

    /*===============================================================================================
                        ELIMINARE I DATI E SVUOTARE LA CACHE PRIMA DI EFFETTURARE IL TEST
        Il cellulare mantiene i dati d'accesso e il LoginTest fallirebbe in quanto non trova le view di input
        per email e password
        ===============================================================================================*/
    @Test
    fun registerTest(){

        // TEST DI REGISTRAZIONE in questo caso l'utente è già registrato e non permette la registrazione
        onView(withId(R.id.btInizia)).perform(click())
        /*onView(withId(R.id.rB_sedentario)).perform(click())
        onView(withId(R.id.sB_agonistico)).perform(click())
        onView(withId(R.id.imageView16)).check(matches(isDisplayed()))
        onView(withId(R.id.bt_AvantiObb)).perform(click())*/
        onView(withId(R.id.rB_uomo)).perform(click())
        onView(withId(R.id.bt_AvantiSesso)).perform(click())
        Espresso.pressBack()
        onView(withId(R.id.rB_donna)).perform(click())
        onView(withId(R.id.bt_AvantiSesso)).perform(click())
        onView(withText("Dati Personali")).check(matches(isDisplayed()))
        onView(withId(R.id.tv_dataNascita)).perform(click())
        val year = 2001
        val month = 1
        val day = 27
        onView(withClassName(Matchers.equalTo(DatePicker::class.java.name))).perform(PickerActions.setDate(year,month,day))
        onView(withId(android.R.id.button1)).perform(click())
        onView(withId(R.id.tE_name)).perform(ViewActions.typeText("Giovanna"))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.tE_surname)).perform(ViewActions.typeText("Rossi"))
        onView(withId(R.id.tE_name)).check(matches(withText("Giovanna")))
        onView(withId(R.id.tE_surname)).check(matches(withText("Rossi")))
        onView(withId(R.id.tv_dataNascita)).check(matches(withText("27-1-2001")))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.bt_AvantiDati)).perform(click())
//        onView(withId(R.id.eT_altezza)).perform(ViewActions.typeText("30"))
        Espresso.closeSoftKeyboard()
//        onView(withId(R.id.bt_AvantiAltezza)).perform(click())
//        onView(withId(R.id.eT_altezza)).check(matches(withText("")))
//        onView(withId(R.id.eT_altezza)).perform(ViewActions.typeText("165"))
        Espresso.closeSoftKeyboard()
//        onView(withId(R.id.bt_AvantiAltezza)).perform(click())
//        onView(withId(R.id.eT_PesoAttuale)).perform(ViewActions.typeText("29"))
        Espresso.closeSoftKeyboard()
//        onView(withId(R.id.bt_AvantiPesoAttuale)).perform(click())
//        onView(withId(R.id.eT_PesoAttuale)).check(matches(withText("")))
//        onView(withId(R.id.eT_PesoAttuale)).perform(ViewActions.typeText("56"))
        Espresso.closeSoftKeyboard()
//        onView(withId(R.id.bt_AvantiPesoAttuale)).perform(click())
//        onView(withId(R.id.cB_calcio)).perform(click())
//        onView(withId(R.id.bt_AvantiPesoObb)).perform(click())
        onView(withId(R.id.btnRegister)).check(matches(withText("Registrati")))
        onView(withId(R.id.InputEmail)).perform(ViewActions.typeText("test@espresso.it"))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.InputPassword)).perform(ViewActions.typeText("123456"))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.InputCorrectPassword)).perform(ViewActions.typeText("123456"))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.btnRegister)).perform(click())


    }

    @Test
    fun LoginTest(){
        /*===============================================================================================
        SUCCESSO nel caso in cui non si ha una cache da ripulire e nessun dato di accesso memorizzato
        ===============================================================================================*/

        onView(withId(R.id.btAccesso)).check(matches(withText("ACCEDI")))
        onView(withId(R.id.btAccesso)).perform(click())
        onView(withId(R.id.InputEmailLogin)).perform(ViewActions.typeText("test@espresso.it"))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.InputPasswordLogin)).perform(ViewActions.typeText("123456"))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.btnLogin)).perform(click())
    }


}