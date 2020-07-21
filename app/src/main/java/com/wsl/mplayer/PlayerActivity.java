package com.wsl.mplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerControlView;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.io.File;

public class PlayerActivity extends AppCompatActivity implements PlayerControlView.VisibilityListener, Player.EventListener {

    private String TAG = "PlayerActivity";
    private PlayerView playerView;

    private Context context = this;
    private SimpleExoPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);


        playerView = findViewById(R.id.player_view);
        playerView.setControllerVisibilityListener(this);

        player = new SimpleExoPlayer.Builder(this).build();
        playerView.setPlayer(player);

        player.addListener((Player.EventListener) this);


    }

    @Override
    protected void onStart() {
        super.onStart();


    }
    int READ_PERMISSION_REQUEST =  1 ;

    @Override
    protected void onResume() {
        super.onResume();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            String [] p = {Manifest.permission.READ_EXTERNAL_STORAGE};
            requestPermissions(p,READ_PERMISSION_REQUEST);

        }else {



        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == READ_PERMISSION_REQUEST) {
            initPlayer();
        }
    }

    void initPlayer(){
        // Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context,
                Util.getUserAgent(context, "com.wsl.mplayer"));
// This is the MediaSource representing the media to be played.

//        Uri uri = Uri.parse("https://storage.googleapis.com/exoplayer-test-media-1/gen-3/screens/dash-vod-single-segment/video-137.mp4");
        File f1 = Environment.getExternalStorageDirectory();
        String path = f1.getAbsolutePath() + "/大明王朝未看/[迅播影院www.XunBo.Cc]大明王朝1566-40.国语.D-vb.rmvb";
        Uri uri = Uri.parse(path);

        MediaSource videoSource =
                new ProgressiveMediaSource.Factory(dataSourceFactory)
                        .createMediaSource(uri);
// Prepare the player with the source.
        player.prepare(videoSource);
        player.setPlayWhenReady(true);
    }

    @Override
    public void onVisibilityChange(int visibility) {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        player.release();

    }

    @Override
    public void onIsPlayingChanged(boolean isPlaying) {
        if (isPlaying) {

        } else {

        }
    }
}
