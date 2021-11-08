package com.uin.stoichiofyapp.Trivia.subTrivia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
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

import okhttp3.internal.http2.ConnectionShutdownException;

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
        if (!ScreenReceiver.wasScreenOn) {
            // THIS IS WHEN ONRESUME() IS CALLED DUE TO A SCREEN STATE CHANGE
            if (Util.SDK_INT >= 24 || player == null){
                releasePlayer();
            }
            System.out.println("SCREEN TURNED ON");
        } else {
            if (Util.SDK_INT >= 24 || player == null){
                initializePlayer();
            }
            // THIS IS WHEN ONRESUME() IS CALLED WHEN THE SCREEN STATE HAS NOT CHANGED
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (ScreenReceiver.wasScreenOn) {
            // THIS IS THE CASE WHEN ONPAUSE() IS CALLED BY THE SYSTEM DUE TO A SCREEN STATE CHANGE
            if (Util.SDK_INT > 24){
                initializePlayer();
            }
            System.out.println("SCREEN TURNED OFF");
        } else {
            if (Util.SDK_INT > 24){
                releasePlayer();
            }
            // THIS IS WHEN ONPAUSE() IS CALLED WHEN THE SCREEN STATE HAS NOT CHANGED
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!ScreenReceiver.wasScreenOn) {
            // THIS IS WHEN ONRESUME() IS CALLED DUE TO A SCREEN STATE CHANGE
            if (Util.SDK_INT >= 24 || player == null){
                releasePlayer();
            }
            System.out.println("SCREEN TURNED ON");
        } else {
            if (Util.SDK_INT >= 24 || player == null){
                initializePlayer();
            }
            // THIS IS WHEN ONRESUME() IS CALLED WHEN THE SCREEN STATE HAS NOT CHANGED
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (ScreenReceiver.wasScreenOn) {
            // THIS IS THE CASE WHEN ONPAUSE() IS CALLED BY THE SYSTEM DUE TO A SCREEN STATE CHANGE
            if (Util.SDK_INT > 24){
                initializePlayer();
            }
            System.out.println("SCREEN TURNED OFF");
        } else {
            if (Util.SDK_INT > 24){
                releasePlayer();
            }
            // THIS IS WHEN ONPAUSE() IS CALLED WHEN THE SCREEN STATE HAS NOT CHANGED
        }


    }

    @Override
    protected void onStop() {
        super.onStop();
        if (ScreenReceiver.wasScreenOn) {
            // THIS IS THE CASE WHEN ONPAUSE() IS CALLED BY THE SYSTEM DUE TO A SCREEN STATE CHANGE
            if (Util.SDK_INT > 24){
                initializePlayer();
            }
            System.out.println("SCREEN TURNED OFF");
        } else {
            if (Util.SDK_INT > 24){
                releasePlayer();
            }
            // THIS IS WHEN ONPAUSE() IS CALLED WHEN THE SCREEN STATE HAS NOT CHANGED
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (ScreenReceiver.wasScreenOn) {
            // THIS IS THE CASE WHEN ONPAUSE() IS CALLED BY THE SYSTEM DUE TO A SCREEN STATE CHANGE
            if (Util.SDK_INT > 24){
                initializePlayer();
            }
            System.out.println("SCREEN TURNED OFF");
        } else {
            if (Util.SDK_INT > 24){
                releasePlayer();
            }
            // THIS IS WHEN ONPAUSE() IS CALLED WHEN THE SCREEN STATE HAS NOT CHANGED
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

//    @Override
//    public boolean onKeyUp(int keyCode, KeyEvent event) {
//        if (event.getKeyCode() == KeyEvent.KEYCODE_POWER){
//            if (Util.SDK_INT > 24){
//                player.stop();
//                releasePlayer();
//            }
//            return true;
//        }
//        return super.onKeyUp(keyCode, event);
//    }
//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (event.getKeyCode() == KeyEvent.KEYCODE_POWER){
//            if (Util.SDK_INT > 24){
//                player.stop();
//                releasePlayer();
//            }
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }


}