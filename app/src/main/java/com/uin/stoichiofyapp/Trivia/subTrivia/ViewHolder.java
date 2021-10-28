package com.uin.stoichiofyapp.Trivia.subTrivia;

import android.app.Application;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.uin.stoichiofyapp.R;

public class ViewHolder extends RecyclerView.ViewHolder {

    View mView;
    SimpleExoPlayer simpleExoPlayer;
    private PlayerView mExoplayerView;


    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        mView = itemView;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        });

    }

    public void setExoPlayer(Application ctx, String title, String url,String subtitle){
        TextView tvTitle = mView.findViewById(R.id.tvTitle);
        TextView tvSubtitle = mView.findViewById(R.id.tvSubtitle);
        mExoplayerView = mView.findViewById(R.id.exoplayer_view);

        tvTitle.setText(title);
        tvSubtitle.setText(subtitle);

        try {
            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter.Builder(ctx).build();
            TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
            simpleExoPlayer = (SimpleExoPlayer) ExoPlayerFactory.newSimpleInstance(ctx);
            Uri video = Uri.parse(url);
            DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("video");
            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
            MediaSource mediaSource = new ExtractorMediaSource(video, dataSourceFactory, extractorsFactory, null, null);
            mExoplayerView.setPlayer(simpleExoPlayer);
            simpleExoPlayer.prepare(mediaSource);
            simpleExoPlayer.setPlayWhenReady(false);


        } catch (Exception e ){
            Log.e("View Holder", "Exoplayer Error " + e.toString());
        }
    }

    private ViewHolder.ClickListener mClickListener;
    public interface ClickListener{
        void onItemClick(View view, int position);
    }

    public void setOnClickListener(ViewHolder.ClickListener clickListener){
        mClickListener = clickListener;
    }

    public boolean releasePlayer(){
//        if (simpleExoPlayer != null){
//            simpleExoPlayer.stop();
//            simpleExoPlayer.setPlayWhenReady(false);
//            simpleExoPlayer.getCurrentPosition();
//            simpleExoPlayer.getCurrentWindowIndex();
//            simpleExoPlayer = null;
//        }
        return true;

    }



}
