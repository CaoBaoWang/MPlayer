package com.wsl.mplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import com.google.android.exoplayer2.ui.PlayerView;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String TAG = "PlayerActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.bt1).setOnClickListener(this);
//
//        File a = getExternalFilesDir(null);
//        File[] f =  getExternalFilesDirs(Environment.DIRECTORY_DOCUMENTS);

        File f1 = Environment.getExternalStorageDirectory();
        String path =  f1.getAbsolutePath()+"大明王朝未看/[迅播影院www.XunBo.Cc]大明王朝1566-40.国语.D-vb.rmvb";


        Log.e(TAG,f1.getAbsolutePath());

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt1:
               Intent i = new Intent(this,PlayerActivity.class);
               startActivity(i);

            break;

            default:
                break;
        }


    }
}
