package com.uin.stoichiofyapp.Silabus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.barteksc.pdfviewer.PDFView
import com.uin.stoichiofyapp.R

class SilabusActivity : AppCompatActivity() {

    lateinit var pdfView: PDFView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_silabus)


        pdfView = findViewById(R.id.pdfView)

        pdfView.fromAsset("silabus.pdf")
            .enableDoubletap(true)
            .load()




    }
}