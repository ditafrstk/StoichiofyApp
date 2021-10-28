package com.uin.stoichiofyapp.Trivia.subTrivia;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.uin.stoichiofyapp.R;

public class FullscreenActivity extends AppCompatActivity {

    private SimpleExoPlayer player;
    private PlayerView playerView;
    TextView tvTitle, tvSubtitle;
    private String url;
    private boolean playWhenReady = false;
    private int currentWindow = 0;
    private long playbackPostion = 0;
    boolean fullscreen = false;
    ImageView fullscreenButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);

//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setTitle("Fullscreen");
//
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setDisplayShowHomeEnabled(true);

        playerView = findViewById(R.id.exoplayer_fullscreen);
        tvTitle = findViewById(R.id.tvTitle);
        tvSubtitle = findViewById(R.id.tvSubtitle);

        fullscreenButton = playerView.findViewById(R.id.exoplayer_fullscreen_icon);


        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        String title = intent.getStringExtra("title");
        String subtitle = intent.getStringExtra("subtitle");

        tvTitle.setText(title);
        tvSubtitle.setText(subtitle);

        fullscreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fullscreen){
                    fullscreenButton.setImageDrawable(ContextCompat.getDrawable(FullscreenActivity.this, R.drawable.ic_fullscreen_expand));
                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
//                    if (getSupportActionBar() != null){
//                        getSup
//                    }
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) playerView.getLayoutParams();
                    params.width = params.MATCH_PARENT;
                    params.height = (int) (200 * getApplicationContext().getResources().getDisplayMetrics().density);
                    playerView.setLayoutParams(params);
                    fullscreen = false;
                } else {
                    fullscreenButton.setImageDrawable(ContextCompat.getDrawable(FullscreenActivity.this, R.drawable.ic_fullscreen_shrink));
                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) playerView.getLayoutParams();
                    params.width = params.MATCH_PARENT;
                    params.height = params.MATCH_PARENT;
                    playerView.setLayoutParams(params);
                    fullscreen = true;


                }
            }
        });
    }

    private MediaSource buildMediaSource (Uri uri){
        DataSource.Factory datasource =
                new DefaultHttpDataSourceFactory("video");
        return new ProgressiveMediaSource.Factory(datasource)
                .createMediaSource(uri);
    }

    private void initializePlayer(){
        player = ExoPlayerFactory.newSimpleInstance(this);
        playerView.setPlayer(player);
        Uri uri = Uri.parse(url);
        MediaSource mediaSource = buildMediaSource(uri);
        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, playbackPostion);
        player.prepare(mediaSource, false, false);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Util.SDK_INT >= 26){
            initializePlayer();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Util.SDK_INT >= 26 || player == null){
//            initializePlayer();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (Util.SDK_INT > 26){
            releasePlayer();
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (Util.SDK_INT > 26){
            releasePlayer();
        }

    }

    private void releasePlayer(){
        if (player != null){
            playWhenReady = player.getPlayWhenReady();
            playbackPostion = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            player = null;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        player.stop();
        releasePlayer();

        final Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }
}