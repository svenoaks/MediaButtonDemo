package com.example.xyz.mediareceiverdemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.media.session.MediaButtonReceiver;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.widget.Toast;

public class MediaService extends Service {
    MediaSessionCompat mediaSession;

    public MediaService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaSession = new MediaSessionCompat(getApplicationContext(), "MEDIA");
        mediaSession.setFlags(MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS | MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);
        PlaybackStateCompat ps = new PlaybackStateCompat.Builder()
                .setActions(PlaybackStateCompat.ACTION_PLAY | PlaybackStateCompat.ACTION_PAUSE | PlaybackStateCompat.ACTION_PLAY_PAUSE)
                .build();
        mediaSession.setPlaybackState(ps);
        mediaSession.setCallback(new MediaSessionCompat.Callback() {
            @Override
            public void onPlay() {
                super.onPlay();
                Toast.makeText(getApplicationContext(), "MEDIA BUTTON PLAY PRESSED", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onPause() {
                super.onPause();
                Toast.makeText(getApplicationContext(), "MEDIA BUTTON PAUSE PRESSED", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.getAction().equals("PLAY_COMMAND"))
        {
            mediaSession.setActive(true);
        }
        else if (intent.getAction().equals("STOP_COMMAND"))
        {
            mediaSession.setActive(false);
        }
        else
        {
            MediaButtonReceiver.handleIntent(mediaSession, intent);
        }
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
