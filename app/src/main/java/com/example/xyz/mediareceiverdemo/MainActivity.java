package com.example.xyz.mediareceiverdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onPlay(View view)
    {
        Intent intent = new Intent(this, MediaService.class);
        intent.setAction("PLAY_COMMAND");
        startService(intent);
    }

    public void onStop(View view)
    {
        Intent intent = new Intent(this, MediaService.class);
        intent.setAction("STOP_COMMAND");
        startService(intent);
    }
}
