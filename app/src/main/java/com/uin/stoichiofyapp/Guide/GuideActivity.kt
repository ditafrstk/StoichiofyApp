package com.uin.stoichiofyapp.Guide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.barteksc.pdfviewer.PDFView
import com.uin.stoichiofyapp.R

class GuideActivity : AppCompatActivity() {

    lateinit var pdfView : PDFView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guide)

        pdfView = findViewById(R.id.pdfView)

        pdfView.fromAsset("Petunjuk Penggunaan.pdf")
            .enableDoubletap(true)
            .load()
    }
}