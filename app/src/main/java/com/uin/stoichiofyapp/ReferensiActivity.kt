package com.uin.stoichiofyapp

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.barteksc.pdfviewer.PDFView

class ReferensiActivity : AppCompatActivity() {

     lateinit var pdfView: PDFView
     lateinit var Uri : Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_referensi)

        pdfView = findViewById(R.id.pdfView)

        pdfView.fromAsset("Refrensi.pdf")
            .swipeHorizontal(false)
            .enableDoubletap(true)
            .load()

    }
}

