package com.uin.stoichiofyapp.Bookmark;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;
import com.uin.stoichiofyapp.Materi.bab.showBabActivity;
import com.uin.stoichiofyapp.R;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SubBookmarkActivity extends AppCompatActivity {

    private PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_bookmark);

        String url = getIntent().getStringExtra("url");
        String gettujuan = getIntent().getStringExtra("tujuan");
        String gettujuan2 = getIntent().getStringExtra("tujuan2");
        String gettujuan3 = getIntent().getStringExtra("tujuan3");
        String judul = getIntent().getStringExtra("judul");

        TextView title = findViewById(R.id.title);
        TextView tujuan = findViewById(R.id.tujuan);
        TextView tujuan2 = findViewById(R.id.tujuan2);
        TextView tujuan3 = findViewById(R.id.tujuan3);
        ImageView btnBack = findViewById(R.id.btn_back);

        title.setText(judul);
        tujuan.setText(gettujuan);
        tujuan2.setText(gettujuan2);
        tujuan3.setText(gettujuan3);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });



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