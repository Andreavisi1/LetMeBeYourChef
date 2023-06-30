package com.example.letmebeyourchef.statistiche

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letmebeyourchef.databaseFB.DiarioDB
import com.example.letmebeyourchef.model.Diario
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class StatisticheViewModel : ViewModel() {
    private val diarioDB = DiarioDB()
    private val auth = FirebaseAuth.getInstance()

    private var _statiticheLiveData  = MutableLiveData<ArrayList<Diario>>()
    val statisticheLivedata : LiveData<ArrayList<Diario>>
        get() = _statiticheLiveData

    private var _kcalAssunte = MutableLiveData<Int>()
    val kcalAssunte : LiveData<Int>
        get() = _kcalAssunte

    private var _litriBevuti = MutableLiveData<Double>()
    val litriBevuti : LiveData<Double>
        get() = _litriBevuti

    private var _grCarboidrati = MutableLiveData<Int>()
    val grCarboidrati : LiveData<Int>
        get() = _grCarboidrati

    private var _grProteine = MutableLiveData<Int>()
    val grProteine : LiveData<Int>
        get() = _grProteine

    private var _grGrassi = MutableLiveData<Int>()
    val grGrassi : LiveData<Int>
        get() = _grGrassi

    private var _giorniAcqua = MutableLiveData<Int>()
    val giorniAcqua : LiveData<Int>
        get() = _giorniAcqua

    private var _getStatistiche = MutableLiveData<Boolean>()
    val getStatistiche : LiveData<Boolean>
        get() = _getStatistiche



    fun ottieniStatistihe(data_inizio: String, data_fine: String)  {
        viewModelScope.launch {
            _statiticheLiveData.value = diarioDB.getStatistiche(data_inizio, data_fine)
            _kcalAssunte.value = 0
            _grCarboidrati.value = 0
            _grProteine.value = 0
            _grGrassi.value = 0
            _litriBevuti.value = 0.0
            _giorniAcqua.value = 0
            for (diario in _statiticheLiveData.value!!){
                _kcalAssunte.value = _kcalAssunte.value!! + (diario.chiloCalorieCena + diario.chiloCalorieColazione + diario.chiloCaloriePranzo + diario.chiloCalorieSpuntino)
                _grCarboidrati.value = _grCarboidrati.value!! + diario.carboidratiTot
                _grProteine.value = _grProteine.value!! + diario.proteineTot
                _grGrassi.value = _grGrassi.value!! + diario.grassiTot
                var acqua = 0
                for(i in 0..7) {

                    if (diario.acqua[i]) {
                        _litriBevuti.value = _litriBevuti.value!! + 0.25
                        acqua +=1
                    }
                    if (acqua == 8) _giorniAcqua.value = _giorniAcqua.value!! + 1
                }

            }
            _getStatistiche.value = true
        }
    }


}