package com.uin.stoichiofyapp.Trivia.subTrivia;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.uin.stoichiofyapp.R;

import java.lang.reflect.Member;

public class SubTriviaActivityJava extends AppCompatActivity {

    RecyclerView mRecylerView;
    FirebaseDatabase mDatabase;
    DatabaseReference reference;
    SimpleExoPlayer player;
    PlayerView playerUI;
    boolean isActivityRunning = false;
    String url,title,subtitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_trivia);

        String key = getIntent().getStringExtra("key");

        mRecylerView = findViewById(R.id.rv_subtrivia);
        mRecylerView.setHasFixedSize(true);
        mRecylerView.setLayoutManager(new LinearLayoutManager(this));
        mDatabase = FirebaseDatabase.getInstance();
        reference = mDatabase.getReference("Trivia").child(key).child("video");


        ImageView btn_back = findViewById(R.id.btnBack);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        FirebaseRecyclerOptions<member> options =
                new FirebaseRecyclerOptions.Builder<member>()
                        .setQuery(reference, member.class).build();

        FirebaseRecyclerAdapter<member, ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<member, ViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull member model) {
                        holder.setExoPlayer(getApplication(), model.getTitle(), model.getUrl(), model.getSubtitle());

                        Log.v("1510", "Datanya = "+model.getTitle());
                        holder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                url = getItem(position).getUrl();
                                title = getItem(position).getTitle();
                                subtitle = getItem(position).getSubtitle();
                                Intent intent = new Intent(SubTriviaActivityJava.this, FullscreenActivity.class);
                                intent.putExtra("url", url);
                                intent.putExtra("title", title);
                                intent.putExtra("subtitle", subtitle);
                                startActivity(intent);

                            }
                        });



                    }

                    @NonNull
                    @Override
                    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.item_subtrivia, parent, false);
                        return new ViewHolder(view);
                    }
                };
        firebaseRecyclerAdapter.startListening();
        mRecylerView.setAdapter(firebaseRecyclerAdapter);
    }




}
