package com.uin.stoichiofyapp.POL

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.barteksc.pdfviewer.PDFView
import com.uin.stoichiofyapp.R

class POLActivity : AppCompatActivity() {

    lateinit var pdfView: PDFView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_polactivity)

        pdfView = findViewById(R.id.pdfView)

        pdfView.fromAsset("Program Learning Outcome.pdf")
            .enableDoubletap(true)
            .load()

    }
}