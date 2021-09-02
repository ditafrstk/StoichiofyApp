package com.uin.stoichiofyapp.Peta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.barteksc.pdfviewer.PDFView
import com.uin.stoichiofyapp.R

class PetaKonsepActivity : AppCompatActivity() {

    lateinit var pdfView : PDFView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_peta_konsep)

        pdfView = findViewById(R.id.pdfView)

        pdfView.fromAsset("Peta Konsep.pdf")
            .enableDoubletap(true)
            .load()

    }
}