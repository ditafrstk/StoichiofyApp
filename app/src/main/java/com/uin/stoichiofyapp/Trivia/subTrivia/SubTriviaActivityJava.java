package com.uin.stoichiofyapp.Trivia.subTrivia;


import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.uin.stoichiofyapp.R;

public class SubTriviaActivityJava extends AppCompatActivity {

    RecyclerView mRecylerView;
    FirebaseDatabase mDatabase;
    DatabaseReference reference;
    SimpleExoPlayer player;
    PlayerView playerUI;
    boolean isActivityRunning = false;


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

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<member, ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<member, ViewHolder>(
                        member.class,
                        R.layout.item_subtrivia,
                        ViewHolder.class,
                        reference
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, member member, int i) {
                        viewHolder.setVideo(getApplication(), member.getTitle(), member.getUrl(), member.getSubtitle());
                        if (viewHolder.releasePlayer()){
                            releasePlayer();
                        }

                    }
                };
        mRecylerView.setAdapter(firebaseRecyclerAdapter);
    }

//    private void releasePlayer(){
//        if (player != null){
//            player.stop();
//            player.getPlayWhenReady();
//            player.getCurrentPosition();
//            player.getCurrentWindowIndex();
//            player = null;
//        }
//    }

    @Override
    public void onPause() {
        super.onPause();
        isActivityRunning = false;
        releasePlayer();
    }

    @Override
    public void onStop() {
        super.onStop();
        isActivityRunning = false;
        releasePlayer();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        releasePlayer();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        releasePlayer();
    }

    private void releasePlayer(){
        if (player != null){
            player.stop();
            player.release();
            player.clearVideoSurface();
            playerUI.getPlayer().release();;


            player = null;
            playerUI =null;
        }
    }


}
