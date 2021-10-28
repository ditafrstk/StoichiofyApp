package com.uin.stoichiofyapp.Materi.bab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uin.stoichiofyapp.R;
import com.uin.stoichiofyapp.Utils.Preferences;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class showBabActivity extends AppCompatActivity {


    private PDFView pdfView;
    DatabaseReference firebaseDatabase;
    DatabaseReference wishDatabase = FirebaseDatabase.getInstance().getReference("User");
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
        String keyParent = getIntent().getStringExtra("keyParent");
        String keyChild = getIntent().getStringExtra("keyChild");
//        String click = "true";


        TextView title = findViewById(R.id.title);
        TextView tujuan = findViewById(R.id.tujuan);
        TextView tujuan2 = findViewById(R.id.tujuan2);
        TextView tujuan3 = findViewById(R.id.tujuan3);
        TextView tvBab = findViewById(R.id.tv_Bab);
        TextView tvJudul = findViewById(R.id.tvTitleBab);
        ImageButton btn_wish = findViewById(R.id.btn_wish);

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

        firebaseDatabase = FirebaseDatabase.getInstance().getReference("Materi")
                        .child(keyParent).child(bab).child(keyChild);

        wishDatabase.child(uname).child("Favourite");

        ArrayList<Favourite> favourites = new ArrayList<Favourite>();
//        wishDatabase.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot getSnap : snapshot.getChildren()){
//                    favourites.add(getSnap.getValue(Favourite.class));
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        btn_wish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addtoUser();
                btn_wish.setImageResource(R.drawable.ic_baseline_turned_in_24);
                btn_wish.setClickable(false);
//                    firebaseDatabase = FirebaseDatabase.getInstance().getReference("Materi")
//                            .child(keyParent).child(bab).child(keyChild).child("click");
//                    firebaseDatabase.setValue("true");
                Toast.makeText(showBabActivity.this,"Berhasil disimpan di Favourite", Toast.LENGTH_LONG).show();
            }
        });

//        if (favourites.isEmpty()){
//            btn_wish.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    addtoUser();
//                    btn_wish.setImageResource(R.drawable.ic_baseline_turned_in_24);
//                    btn_wish.setClickable(false);
////                    firebaseDatabase = FirebaseDatabase.getInstance().getReference("Materi")
////                            .child(keyParent).child(bab).child(keyChild).child("click");
////                    firebaseDatabase.setValue("true");
//                    Toast.makeText(showBabActivity.this,"Berhasil disimpan di Favourite", Toast.LENGTH_LONG).show();
//                }
//            });
//        } else {
//            if (favourites.contains(addtoWish(url, gettujuan, gettujuan, gettujuan3, judul))) {
//                Toast.makeText(showBabActivity.this, "Sudah didalam favourite", Toast.LENGTH_LONG).show();
//            } else {
//                btn_wish.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        addtoUser();
//                        btn_wish.setImageResource(R.drawable.ic_baseline_turned_in_24);
//                        btn_wish.setClickable(false);
////                    firebaseDatabase = FirebaseDatabase.getInstance().getReference("Materi")
////                            .child(keyParent).child(bab).child(keyChild).child("click");
////                    firebaseDatabase.setValue("true");
//                        Toast.makeText(showBabActivity.this,"Berhasil disimpan di Favourite", Toast.LENGTH_LONG).show();
//                    }
//                });
//            }
//        }







        ImageView btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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

    private void addtoUser(){
        String Url = getIntent().getStringExtra("pdf");
        String Gettujuan = getIntent().getStringExtra("tujuan");
        String Gettujuan2 = getIntent().getStringExtra("tujuan2");
        String Gettujuan3 = getIntent().getStringExtra("tujuan3");
        String Title1 = getIntent().getStringExtra("title");

        SubBab subBab = new SubBab(Url, Title1, Gettujuan, Gettujuan2, Gettujuan3);

        preferences = new Preferences(this);
        String uname = preferences.getValues("username");
        assert uname != null;

        wishDatabase.child(uname).child("Book Mark").push().setValue(subBab);
    }

    public Favourite addtoWish(final String url, String tujuan, String tujuan2, String tujuan3, String judul) {
        Favourite favourite = new Favourite(
                url, judul, tujuan, tujuan2, tujuan3);
        return favourite;
    }


}