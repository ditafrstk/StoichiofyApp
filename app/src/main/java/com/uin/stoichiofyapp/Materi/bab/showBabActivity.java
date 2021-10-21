package com.uin.stoichiofyapp.Materi.bab;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.uin.stoichiofyapp.R;
import com.uin.stoichiofyapp.Utils.Preferences;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class showBabActivity extends AppCompatActivity {


    private PDFView pdfView;
    DatabaseReference firebaseDatabase;
    Preferences preferences;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_bab);

        String url = getIntent().getStringExtra("pdf");
        String gettujuan = getIntent().getStringExtra("tujuan");
        String gettujuan2 = getIntent().getStringExtra("tujuan2");
        String gettujuan3 = getIntent().getStringExtra("tujuan3");
        String title1 = getIntent().getStringExtra("title");
        String bab = getIntent().getStringExtra("bab");
        String judul = getIntent().getStringExtra("judul");

        TextView title = findViewById(R.id.title);
        TextView tujuan = findViewById(R.id.tujuan);
        TextView tujuan2 = findViewById(R.id.tujuan2);
        TextView tujuan3 = findViewById(R.id.tujuan3);
        TextView tvBab = findViewById(R.id.tv_Bab);
        TextView tvJudul = findViewById(R.id.tvTitleBab);


        tvBab.setText(bab);
        tvJudul.setText(judul);
        title.setText(title1);
        tujuan.setText(gettujuan);
        if (gettujuan2 == null){
            tujuan2.setVisibility(View.GONE);
        }
        tujuan2.setText(gettujuan2);
        tujuan3.setText(gettujuan3);


        preferences = new Preferences(this);
        String uname = preferences.getValues("username");

        assert uname != null;
        firebaseDatabase = FirebaseDatabase.getInstance().getReference("User")
                        .child(uname);


        ImageView btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        btnSave.setOnClickListener(
//
//        );

        pdfView = findViewById(R.id.pdfView);

        new PdfDownload().execute(url);
    }

    private class PdfDownload extends AsyncTask<String, Void, InputStream> {
        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream = null;

            try {
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                if (urlConnection.getResponseCode() == 200){
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            pdfView.fromStream(inputStream)
                    .enableDoubletap(true)
                    .enableSwipe(true)
                    .load();
        }
    }



}