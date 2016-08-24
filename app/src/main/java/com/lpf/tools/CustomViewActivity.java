package com.lpf.tools;

import android.app.Service;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.lpf.tools.views.VolumeView;

public class CustomViewActivity extends AppCompatActivity {

    private VolumeView mVolumeView;
    private int max;
    AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
    }
}
