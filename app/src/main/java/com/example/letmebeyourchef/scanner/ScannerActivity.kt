package com.example.letmebeyourchef.scanner

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.letmebeyourchef.aggiungi_ingrediente.AggiungiIngredienteActivity
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView

class ScannerActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {

    var scannerView : ZXingScannerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        scannerView = ZXingScannerView(this)
        setContentView(scannerView)
        setPermission()
    }

    override fun handleResult(p0: Result?) {
        val bottone = intent.extras!!.getString("bottone")
        val intent = Intent(applicationContext, AggiungiIngredienteActivity::class.java)
        intent.putExtra("upc", p0.toString())
        intent.putExtra("bottone",bottone)
        startActivity(intent)
        finish()
    }

    override fun onResume() {
        super.onResume()
        scannerView?.setResultHandler(this)
        scannerView?.startCamera()
    }

    override fun onStop() {
        super.onStop()
        scannerView?.stopCamera()
    }

    override fun onBackPressed() {
        val bottone = intent.extras!!.getString("bottone")
        val intent = Intent(applicationContext, AggiungiIngredienteActivity::class.java)
        intent.putExtra("bottone",bottone)
        startActivity(intent)
        finish()
    }

    // Richiesta dei permessi
    private fun setPermission() {
        val permission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            makeRequest()
        }
    }

        private fun makeRequest(){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA),
                1)

        }

        override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
        ) {
            super.onRequestPermissionsResult(requestCode,permissions,grantResults)

            when(requestCode){
                1 -> {
                    if(grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(applicationContext, "Camera permissions needed", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
}