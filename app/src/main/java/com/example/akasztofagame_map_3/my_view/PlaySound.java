package com.example.akasztofagame_map_3.my_view;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.akasztofagame_map_3.R;

public class PlaySound {
    private PlaySound() {
    }

    public static void playmp3(Context context, Boolean win) {

        MediaPlayer mp;

        mp = win ? MediaPlayer.create(context, R.raw.winmp3) : MediaPlayer.create(context, R.raw.losemp3);

        mp.seekTo(0);
        mp.start();
    }
}
