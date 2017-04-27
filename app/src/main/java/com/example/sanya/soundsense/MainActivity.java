package com.example.sanya.soundsense;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setup the onclick to the images
        ImageView heartImage = (ImageView) findViewById(R.id.musicfortheheart);
        ImageView mindImage = (ImageView) findViewById(R.id.musicforthemind);
        ImageView randomImage = (ImageView) findViewById(R.id.random);
        ImageView playlistImage = (ImageView) findViewById(R.id.playlist_icon);

        heartImage.setOnClickListener(this);
        mindImage.setOnClickListener(this);
        randomImage.setOnClickListener(this);
        playlistImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent startMusic;
        // each image click starts the proper intent
        switch (v.getId()) {
            case R.id.musicfortheheart:
                startMusic = new Intent(this, ForTheHeart.class);
                startActivity(startMusic);
                break;
            case R.id.musicforthemind:
                startMusic = new Intent(this, ForTheMind.class);
                startActivity(startMusic);
                break;
            case R.id.random:
                startMusic = new Intent(this, MusicPlayer.class);
                startMusic.putExtra("track", "*random*");
                startMusic.putExtra("mood", "*random*");
                startMusic.putExtra("rating", 0);
                startActivity(startMusic);
                break;
            case R.id.playlist_icon:
                startMusic = new Intent(this, SelectList.class);
                startActivity(startMusic);
                break;
            default:
                break;
        }
    }
}
